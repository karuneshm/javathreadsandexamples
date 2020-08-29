package hackerRank;

import java.util.*;

public class Solution {
	 
	
	 
    public static class PlayerStatisticsCollectorImpl implements PlayerStatisticsCollector {
    	
    	static Set<Player> players = new HashSet<Player>();
    	
        @Override
		public void putNewInnings(String playerName, int runs) {
			if (players != null) {
				Player player = isExist(players, playerName);
				if (player == null) {
					Player newPlayer = new Player(playerName, runs, 1);
					players.add(newPlayer);

				} else {
					player.setRuns(player.getRuns() + runs);
					player.setCount(player.getCount() + 1);

				}
			}

		}

        

		@Override
        public double getAverageRuns(String playerName){
			Player player = isExist(players, playerName);
			double runs = player.getRuns();
			int count = player.getCount();
			double result = runs/count;
			return result;
        }

        @Override
        public int getInningsCount(String playerName){
        	Player player = isExist(players, playerName);
        	return player.getCount();
        }
        
        private Player isExist(Set<Player> players, String playerName) {
			
        	for(Player player : players) {
        		if(playerName.equals(player.getPlayerName()))
        		{
        			return player;
        		}
        	}
			return null;
		}
        
        class Player {
        	private String playerName;
        	private int runs;
        	private int count;
        	
    		public Player(String playerName, int runs , int count) {
    			this.playerName = playerName;
    			this.runs = runs;
    			this.count = count;
    		}

    		public String getPlayerName() {
    			return playerName;
    		}

    		public void setPlayerName(String playerName) {
    			this.playerName = playerName;
    		}

    		public int getRuns() {
    			return runs;
    		}

    		public void setRuns(int runs) {
    			this.runs = runs;
    		}

    		public int getCount() {
    			return count;
    		}

    		public void setCount(int count) {
    			this.count = count;
    		}

        	
        }
        
        
    }
    
    
    
    

    ////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

    public interface PlayerStatisticsCollector {
        // This is an input. Make note of this player inning.  Runs is a non negative integer.
        void putNewInnings(String player, int runs);

        // Get the runs average(mathematical average) for a player
        double getAverageRuns(String player);

        // Get the total number of innings for the player
        int getInningsCount(String player);
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numLines = Integer.parseInt(scanner.nextLine());
		int currentLine = 0;
		while (currentLine++ < numLines) {
			final PlayerStatisticsCollector stats = new PlayerStatisticsCollectorImpl();
			final Set<String> players = new TreeSet<>();

			String line = scanner.nextLine();
			String[] inputs = line.split(",");
			for (int i = 0; i < inputs.length; ++i) {
				String[] tokens = inputs[i].split(" ");
				final String player = tokens[0];
				players.add(player);
				final int runs = Integer.parseInt(tokens[1]);

				stats.putNewInnings(player, runs);

			}

			for (String player : players) {
				System.out.println(String.format("%s %.4f %d", player, stats.getAverageRuns(player),
						stats.getInningsCount(player)));
			}

		}
		scanner.close();

	}
}

