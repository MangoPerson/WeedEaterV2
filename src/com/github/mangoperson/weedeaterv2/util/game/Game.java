package com.github.mangoperson.weedeaterv2.util.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Game extends ListenerAdapter{
	Timer timer = new Timer();
	Random rand = new Random();
	GuildMessageReactionAddEvent revent = null;
	Matrix mat = null;
	public Message message = null;
	public Member member = null;
	TextChannel channel = null;
	int x = 0;
	int y = 1;
	int xp = 0;
	int yp = 1;
	int cacheIndex = 0;
	int length = 2;
	boolean inGame = false;
	boolean move = true;
	int[] foodPos = {6, 5};
	int botIndex = 0;
	
	List<MatTerm> termCache = new ArrayList<MatTerm>(length);
	List<MatTerm> obCache = new ArrayList<MatTerm>();
	
	public Game(GuildMessageReceivedEvent event) {
		this.message = event.getMessage();
		this.member = event.getMember();
		this.channel = event.getChannel();
	}
	
	public void start() {
		if(!inGame) {
			mat = new Matrix(12, 10, 2);
			move = true;
			x = 0;
			y = 1;
			xp = 0;
			yp = 1;
			mat.setTerm(x, y, 1);
			mat.setTerm(foodPos[0],  foodPos[1], 6);
			String display = mat.getDisplay();
			for(int i = 0; i < length; i++) {
				termCache.add(new MatTerm(mat, 0, 1));
			}
			
			display = display.replace("1.0", "🔴");
			display = display.replace("2.0", "🟠");
			display = display.replace("3.0", "🟡");
			display = display.replace("4.0", "🟢"); 
			display = display.replace("5.0", "🔵");
			display = display.replace("6.0", "🟣");
			
			channel.getJDA().addEventListener(this);
			inGame = true;
			
			channel.sendMessage(display).queue(message -> {
				this.message = message;
				message.addReaction("🔼").queue();
				message.addReaction("🔽").queue();
				message.addReaction("◀").queue();
				message.addReaction("▶").queue();
				message.addReaction("❌").queue();
			});
			timer.scheduleAtFixedRate(new Task(), 0, 1100);
		}
	}
	
	public void update(GuildMessageReactionAddEvent event) {
		String emote = event.getReactionEmote().getName();
		if(emote.equals("🔼")) y -= 1;
		if(emote.equals("▶")) x += 1;
		if(emote.equals("🔽")) y += 1;
		if(emote.equals("◀")) x -= 1;
		if(emote.equals("❌")) {
			EmbedBuilder embed = new EmbedBuilder();
			
			embed.setTitle("GAME OVER");
			embed.setColor(0xff0000);
			embed.addField("Score: ", Integer.toString(length), true);
			
			channel.sendMessage(embed.build()).queue();
			
			message.delete().queue();
			x = 0;
			y = 1;
			xp = 0;
			yp = 1;
			inGame = false;
			timer.cancel();
			
		}
		if(inGame) {
			move = true;
			if(x >= mat.width || x < 0 || y > mat.height || y < 0) {
				move = false;
				EmbedBuilder embed = new EmbedBuilder();
				
				embed.setTitle("GAME OVER");
				embed.setColor(0xff0000);
				embed.addField("Score: ", Integer.toString(length), true);
				
				channel.sendMessage(embed.build()).queue();
				
				message.delete().queue();
				x = 0;
				y = 1;
				xp = 0;
				yp = 1;
				inGame = false;
				timer.cancel();
			}
			termCache.forEach(term -> {
				if(term.x == x && term.y == y) {
					move = false;
					
					EmbedBuilder embed = new EmbedBuilder();
					
					embed.setTitle("GAME OVER");
					embed.setColor(0xff0000);
					embed.addField("Score: ", Integer.toString(length), true);
					
					channel.sendMessage(embed.build()).queue();
					
					message.delete().queue();
					x = 0;
					y = 1;
					xp = 0;
					yp = 1;
					inGame = false;
					timer.cancel();
				}
			});
			if(move) {
				termCache.set(cacheIndex, mat.retreiveTerm(x, y));
				cacheIndex += 1;
				if(cacheIndex == length) {
					cacheIndex = 0;
				}
				mat.fill(2);
				
				termCache.forEach(term -> {
					term.set(1);
				});
				
				mat.setTerm(foodPos[0], foodPos[1], 6);
				mat.setTerm(x, y, 5);
				xp = x;
				yp = y;
				
				if(foodPos[0] == x && foodPos[1] == y) {
					foodPos[0] = rand.nextInt(12);
					foodPos[1] = rand.nextInt(10); 
					length++;
					int addIndex = cacheIndex - length;
					if(addIndex < 0) {
						addIndex += length;
					}
					termCache.add(termCache.get(addIndex));
				}
			}
			if(!move) {
				x = xp;
				y = yp;
			}
			if(x >= mat.width) x = mat.width - 1;
			if(x < 0) x = 0;
			if(y > mat.height) y = mat.height;
			if(y < 0) y = 0;
				
			String display = mat.getDisplay();
			display = display.replace("1.0", "🔴");
			display = display.replace("2.0", "🟠");
			display = display.replace("3.0", "🟡");
			display = display.replace("4.0", "🟢");
			display = display.replace("5.0", "🔵");
			display = display.replace("6.0", "🟣");
			
			this.message.editMessage(display).queue();
			event.getReaction().removeReaction(event.getUser()).queue();
		}
	}
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
		if(inGame && event.getUser().equals(member.getUser())) {
			revent = event;
		}
	}
	class Task extends TimerTask {
		public void run() {
			if(revent != null) update(revent);
		}
	}
}
