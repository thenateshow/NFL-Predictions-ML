import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.HashMap;
import java.util.*;
import java.lang.Math;

public class Parser{
	//list : week, year, day/time*, team, opposing team, home/away*, points scored, points against, yards gained, yards given up, turnovers, turnovers (opponent), win/loss
	//week
	//	1-17 - reg season
	//	18 - wild card
	//	19 - division
	//	20 - conf champ
	//	21 - super bowl
	//day/time
	//	0 - thursday
	//	1 - sunday 1pm
	//	2 - sunday 4pm
	//	3 - sunday 8pm
	//	4 - monday
	//	5 - other
	//home/away
	//	0 - home
	//	1 - away
	//win/loss
	//	0 - loss
	//	1 - win
	List<List<Integer>> fortyniners = new ArrayList<List<Integer>>();
	List<List<Integer>> bears = new ArrayList<List<Integer>>();
	List<List<Integer>> bengals = new ArrayList<List<Integer>>();
	List<List<Integer>> bills = new ArrayList<List<Integer>>();
	List<List<Integer>> broncos = new ArrayList<List<Integer>>();
	List<List<Integer>> browns = new ArrayList<List<Integer>>();
	List<List<Integer>> buccaneers = new ArrayList<List<Integer>>();
	List<List<Integer>> cardinals = new ArrayList<List<Integer>>();
	List<List<Integer>> chargers = new ArrayList<List<Integer>>();
	List<List<Integer>> chiefs = new ArrayList<List<Integer>>();
	List<List<Integer>> colts = new ArrayList<List<Integer>>();
	List<List<Integer>> cowboys = new ArrayList<List<Integer>>();
	List<List<Integer>> dolphins = new ArrayList<List<Integer>>();
	List<List<Integer>> eagles = new ArrayList<List<Integer>>();
	List<List<Integer>> falcons = new ArrayList<List<Integer>>();
	List<List<Integer>> football_team = new ArrayList<List<Integer>>();
	List<List<Integer>> giants = new ArrayList<List<Integer>>();
	List<List<Integer>> jaguars = new ArrayList<List<Integer>>();
	List<List<Integer>> jets = new ArrayList<List<Integer>>();
	List<List<Integer>> lions = new ArrayList<List<Integer>>();
	List<List<Integer>> packers = new ArrayList<List<Integer>>();
	List<List<Integer>> panthers = new ArrayList<List<Integer>>();
	List<List<Integer>> patriots = new ArrayList<List<Integer>>();
	List<List<Integer>> raiders = new ArrayList<List<Integer>>();
	List<List<Integer>> rams = new ArrayList<List<Integer>>();
	List<List<Integer>> ravens = new ArrayList<List<Integer>>();
	List<List<Integer>> saints = new ArrayList<List<Integer>>();
	List<List<Integer>> seahawks = new ArrayList<List<Integer>>();
	List<List<Integer>> steelers = new ArrayList<List<Integer>>();
	List<List<Integer>> texans = new ArrayList<List<Integer>>();
	List<List<Integer>> titans = new ArrayList<List<Integer>>();
	List<List<Integer>> vikings = new ArrayList<List<Integer>>();

	HashMap<String, Integer> teams_num = new HashMap<String, Integer>();
	HashMap<Integer, List<List<Integer>>> teams = new HashMap<Integer, List<List<Integer>>>();

	List<Double> avg_score = new ArrayList<Double>();
	List<Double> avg_score_against = new ArrayList<Double>();
	List<Double> avg_yards = new ArrayList<Double>();
	List<Double> avg_yards_against = new ArrayList<Double>();
	List<Double> avg_turnovers = new ArrayList<Double>();
	List<Double> avg_turnovers_against = new ArrayList<Double>();

	List<List<Double>> team_weights = new ArrayList<List<Double>>();
	List<List<Double>> team_mean_win = new ArrayList<List<Double>>();
	List<List<Double>> team_var_win = new ArrayList<List<Double>>();
	List<List<Double>> team_mean_lose = new ArrayList<List<Double>>();
	List<List<Double>> team_var_lose = new ArrayList<List<Double>>();
	List<Double> team_prob_win = new ArrayList<Double>();
	List<Double> team_prob_lose = new ArrayList<Double>();
	
	//in 'test_games', each game has an entry for each team
	//i.e. game 1 is test_games.get(0) AND test_games.get(1)
	List<List<Double>> test_games = new ArrayList<List<Double>>();

	int accuracy = 0;
	int global_correct = 0;
	int global_total = 0;

	List<String> perceptron_accuracies = new ArrayList<String>();
	List<String> naive_bayes_accuracies = new ArrayList<String>();
	
	public Parser(){
		final File seasons = new File("../files/seasons");
		final File test_season = new File("../files/test");
		teams_num.put("fortyniners", 0);
		teams_num.put("bears", 1);
		teams_num.put("bengals", 2);
		teams_num.put("bills", 3);
		teams_num.put("broncos", 4);
		teams_num.put("browns", 5);
		teams_num.put("buccaneers", 6);
		teams_num.put("cardinals", 7);
		teams_num.put("chargers", 8);
		teams_num.put("chiefs", 9);
		teams_num.put("colts", 10);
		teams_num.put("cowboys", 11);
		teams_num.put("dolphins", 12);
		teams_num.put("eagles", 13);
		teams_num.put("falcons", 14);
		teams_num.put("football_team", 15);
		teams_num.put("giants", 16);
		teams_num.put("jaguars", 17);
		teams_num.put("jets", 18);
		teams_num.put("lions", 19);
		teams_num.put("packers", 20);
		teams_num.put("panthers", 21);
		teams_num.put("patriots", 22);
		teams_num.put("raiders", 23);
		teams_num.put("rams", 24);
		teams_num.put("ravens", 25);
		teams_num.put("saints", 26);
		teams_num.put("seahawks", 27);
		teams_num.put("steelers", 28);
		teams_num.put("texans", 29);
		teams_num.put("titans", 30);
		teams_num.put("vikings", 31);

		teams.put(0, fortyniners);
		teams.put(1, bears);
		teams.put(2, bengals);
		teams.put(3, bills);
		teams.put(4, broncos);
		teams.put(5, browns);
		teams.put(6, buccaneers);
		teams.put(7, cardinals);
		teams.put(8, chargers);
		teams.put(9, chiefs);
		teams.put(10, colts);
		teams.put(11, cowboys);
		teams.put(12, dolphins);
		teams.put(13, eagles);
		teams.put(14, falcons);
		teams.put(15, football_team);
		teams.put(16, giants);
		teams.put(17, jaguars);
		teams.put(18, jets);
		teams.put(19, lions);
		teams.put(20, packers);
		teams.put(21, panthers);
		teams.put(22, patriots);
		teams.put(23, raiders);
		teams.put(24, rams);
		teams.put(25, ravens);
		teams.put(26, saints);
		teams.put(27, seahawks);
		teams.put(28, steelers);
		teams.put(29, texans);
		teams.put(30, titans);
		teams.put(31, vikings);

		//making each teams list of games
		list_files_in_folder(seasons);

		//----------------------------------------------PERCEPTRONS----------------------------------------------//
		//team averages based off of games, used for test data in perceptron
		for(int i = 0; i < 32; i++){
			get_team_avgs(teams.get(i));
		}

		//get best weights for each team
		for(int i = 0; i < 32; i++){
			perceptron_train_teams(teams.get(i));
		}

		//test perceptron
		list_files_in_folder_test(test_season);
		System.out.println("Perceptrons: " + global_correct + "/" + global_total);

		//----------------------------------------------NAIVE BAYES----------------------------------------------//
		//get probability, mean & varience of each param, for each team, for win and lose
		for(int i = 0; i < 32; i++){
			naive_get_stats(teams.get(i));
		}

		//test naive bayes on training data
		for(int i = 0; i < 32; i++){
			List<List<Double>> current_team = new ArrayList<List<Double>>();
			List<Double> current_game = new ArrayList<Double>();
			for(List<Integer> l : teams.get(i)){
				for(int j = 0; j < l.size(); j++){
					current_game.add(0.0+l.get(j));
				}
				current_team.add(current_game);
				current_game = new ArrayList<Double>();
			}
			test_naive_on_training(current_team);
		}
		//test naive bayes on test data
		test_naive();

		//----------------------------------------------K NEAREST NEIGHBORS----------------------------------------------//
		test_knn();

		//System.out.println(perceptron_accuracies);
		//System.out.println(naive_bayes_accuracies);
	}

	public void test_knn(){
		int total_counter = 0;
		int correct_counter = 0;

		int k = 47;
		//System.out.println("k=" + k);
		for(int i = 0; i < test_games.size(); i+=2){
			total_counter++;
			List<Double> team_a = test_games.get(i);
			List<Double> team_b = test_games.get(i+1);
			int a_index = (int)Math.round(team_a.get(3));
			int b_index = (int)Math.round(team_b.get(3));
			List<List<Double>> a_data = new ArrayList<List<Double>>();
			List<List<Double>> b_data = new ArrayList<List<Double>>();
			for(int j = 0; j < teams.get(a_index).size(); j++){
				List<Double> temp = new ArrayList<Double>();
				for(int n = 0; n < teams.get(a_index).get(j).size(); n++){
					temp.add(0.0+teams.get(a_index).get(j).get(n));
				}
				a_data.add(temp);
			}
			for(int j = 0; j < teams.get(b_index).size(); j++){
				List<Double> temp = new ArrayList<Double>();
				for(int n = 0; n < teams.get(b_index).get(j).size(); n++){
					temp.add(0.0+teams.get(b_index).get(j).get(n));
				}
				b_data.add(temp);
			}
			List<Double> a_dist = new ArrayList<Double>();
			List<Integer> a_indexes = new ArrayList<Integer>();
			for(int j = 0; j < a_data.size(); j++){
				double temp_dist = 0;
				for(int n = 0; n < team_a.size(); n++){
					temp_dist += Math.pow((a_data.get(j).get(n) - team_a.get(n)), 2);
				}
				temp_dist = Math.sqrt(temp_dist);
				boolean added = false;
				for(int n = 0; n < a_dist.size()-1; n++){
					if(a_dist.get(n) <= temp_dist && a_dist.get(n+1) >= temp_dist){
						a_dist.add(n+1, temp_dist);
						a_indexes.add(n+1, j);
						added = true;
						n = a_dist.size();
					}
				}
				if(!added){
					if(a_dist.size() == 0){
						a_dist.add(temp_dist);
						a_indexes.add(j);
					}
					else{
						if(a_dist.get(0) < temp_dist){
							a_dist.add(temp_dist);
							a_indexes.add(j);
						}
						else{
							a_dist.add(0, temp_dist);
							a_indexes.add(0, j);
						}
					}
				}
			}
			//System.out.println(a_dist);
			List<Double> b_dist = new ArrayList<Double>();
			List<Integer> b_indexes = new ArrayList<Integer>();
			for(int j = 0; j < b_data.size(); j++){
				double temp_dist = 0;
				for(int n = 0; n < team_b.size(); n++){
					temp_dist += Math.pow((b_data.get(j).get(n) - team_b.get(n)), 2);
				}
				temp_dist = Math.sqrt(temp_dist);
				boolean added = false;
				for(int n = 0; n < b_dist.size()-1; n++){
					if(b_dist.get(n) <= temp_dist && b_dist.get(n+1) >= temp_dist){
						b_dist.add(n+1, temp_dist);
						b_indexes.add(n+1, j);
						added = true;
						n = b_dist.size();
					}
				}
				if(!added){
					if(b_dist.size() == 0){
						b_dist.add(temp_dist);
						b_indexes.add(j);
					}
					else{
						if(b_dist.get(0) < temp_dist){
							b_dist.add(temp_dist);
							b_indexes.add(j);
						}
						else{
							b_dist.add(0, temp_dist);
							b_indexes.add(0, j);
						}
					}
				}
			}

			//find k nearest neighbors for each
			int a_win_count = 0;
			int a_lose_count = 0;
			int b_win_count = 0;
			int b_lose_count = 0;
			for(int m = 0; m < k; m++){
				if(a_data.get(a_indexes.get(m)).get(a_data.get(a_indexes.get(m)).size() - 1) == 0){
					a_lose_count++;
				}
				else{
					a_win_count++;
				}
				if(b_data.get(b_indexes.get(m)).get(b_data.get(b_indexes.get(m)).size() - 1) == 0){
					b_lose_count++;
				}
				else{
					b_win_count++;
				}
			}
			if(a_win_count > a_lose_count){
				if(a_win_count > b_win_count){
					correct_counter++;
				}
			}
			else{
				if(b_lose_count > a_lose_count){
					correct_counter++;
				}
			}
			//System.out.println(a_win_count + " " + a_lose_count + " " + b_win_count + " " + b_lose_count);
		}
		System.out.println("KNN:         " + correct_counter + "/" + total_counter);
	}

	public void test_naive_on_training(List<List<Double>> test_games_1){
		int total_counter = 0;
		int correct_counter = 0;
		for(int i = 0; i < test_games_1.size(); i+=2){
			total_counter++;
			List<Double> team_a = test_games_1.get(i);
			int a_index = (int)Math.round(team_a.get(3));
			double a_win_const = 0.0;
			double a_lose_const = 0.0;
			double a_win_exp = 0.0;
			double a_lose_exp = 0.0;

			double a_win = team_prob_win.get(a_index);
			double a_lose = team_prob_lose.get(a_index);

			for(int j = 0; j < team_a.size() - 1; j++){
				if(j != 3 && j != 4){
					a_win_const = 1/(Math.sqrt(2*(Math.PI)*team_var_win.get(a_index).get(j)));
					a_lose_const = 1/(Math.sqrt(2*(Math.PI)*team_var_lose.get(a_index).get(j)));

					a_win_exp = (0-((team_a.get(j)-team_mean_win.get(a_index).get(j))*(team_a.get(j)-team_mean_win.get(a_index).get(j))))/(2*team_var_win.get(a_index).get(j));
					a_lose_exp = (0-((team_a.get(j)-team_mean_lose.get(a_index).get(j))*(team_a.get(j)-team_mean_lose.get(a_index).get(j))))/(2*team_var_lose.get(a_index).get(j));
					
					a_win *= Math.log(Math.pow(a_win_const, a_win_exp));
					a_lose *= Math.log(Math.pow(a_lose_const, a_lose_exp));
				}
			}
			if(a_win > a_lose){
				if(team_a.get(team_a.size() - 1) == 0.0){
					correct_counter++;
				}
			}
			else{
				if(team_a.get(team_a.size() - 1) == 1.0){
					correct_counter++;
				}
			}
		}
		//System.out.println(correct_counter + "/" + total_counter);
		naive_bayes_accuracies.add(correct_counter + "/" + total_counter);
	}

	public void test_naive(){
		int total_counter = 0;
		int correct_counter = 0;
		for(int i = 0; i < test_games.size(); i+=2){
			total_counter++;
			List<Double> team_a = test_games.get(i);
			List<Double> team_b = test_games.get(i+1);
			int a_index = (int)Math.round(team_a.get(3));
			int b_index = (int)Math.round(team_b.get(3));
			double a_win_const = 0.0;
			double a_lose_const = 0.0;
			double b_win_const = 0.0;
			double b_lose_const = 0.0;
			double a_win_exp = 0.0;
			double a_lose_exp = 0.0;
			double b_win_exp = 0.0;
			double b_lose_exp = 0.0;

			double a_win = team_prob_win.get(a_index);
			double a_lose = team_prob_lose.get(a_index);
			double b_win = team_prob_win.get(b_index);
			double b_lose = team_prob_lose.get(b_index);

			for(int j = 0; j < team_a.size() - 1; j++){
				if(j != 3 && j != 4){
					a_win_const = 1/(Math.sqrt(2*(Math.PI)*team_var_win.get(a_index).get(j)));
					a_lose_const = 1/(Math.sqrt(2*(Math.PI)*team_var_lose.get(a_index).get(j)));
					b_win_const = 1/(Math.sqrt(2*(Math.PI)*team_var_win.get(b_index).get(j)));
					b_lose_const = 1/(Math.sqrt(2*(Math.PI)*team_var_lose.get(b_index).get(j)));

					a_win_exp = (0-((team_a.get(j)-team_mean_win.get(a_index).get(j))*(team_a.get(j)-team_mean_win.get(a_index).get(j))))/(2*team_var_win.get(a_index).get(j));
					a_lose_exp = (0-((team_a.get(j)-team_mean_lose.get(a_index).get(j))*(team_a.get(j)-team_mean_lose.get(a_index).get(j))))/(2*team_var_lose.get(a_index).get(j));
					b_win_exp = (0-((team_b.get(j)-team_mean_win.get(b_index).get(j))*(team_b.get(j)-team_mean_win.get(b_index).get(j))))/(2*team_var_win.get(b_index).get(j));
					b_lose_exp = (0-((team_b.get(j)-team_mean_lose.get(b_index).get(j))*(team_b.get(j)-team_mean_lose.get(b_index).get(j))))/(2*team_var_lose.get(b_index).get(j));
					
					a_win *= Math.log(Math.pow(a_win_const, a_win_exp));
					a_lose *= Math.log(Math.pow(a_lose_const, a_lose_exp));
					b_win *= Math.log(Math.pow(b_win_const, b_win_exp));
					b_lose *= Math.log(Math.pow(b_lose_const, b_lose_exp));
				}
			}
			//System.out.println(a_win + " " + a_lose + " " + b_win + " " + b_lose);
			if(a_win > a_lose && a_win > b_win && a_win > b_lose){
				
			}
			else if(a_lose > a_win && a_lose > b_win && a_lose > b_lose){
				correct_counter++;
			}
			else if(b_win > a_win && b_win > a_lose && b_win > b_lose){
				correct_counter++;
			}
			else{
				
			}
		}
		System.out.println("Naive Bayes: " + correct_counter + "/" + total_counter);
	}

	public void naive_get_stats(List<List<Integer>> team){
		double total_games = 0;
		double win = 0;
		double loss = 0;
		List<Double> totals_win = new ArrayList<Double>();
		List<Double> totals_lose = new ArrayList<Double>();
		List<Double> var_win = new ArrayList<Double>();
		List<Double> var_lose = new ArrayList<Double>();
		for(int i = 0; i < team.get(0).size() - 1; i++){
			totals_win.add(0.0);
			totals_lose.add(0.0);
			var_win.add(0.0);
			var_lose.add(0.0);
		}
		for(List<Integer> l : team){
			total_games++;
			if(l.get(l.size() - 1) == 0){
				loss++;
				for(int i = 0; i < l.size() - 1; i++){
					if(!(i == 3 || i == 4)){
						totals_lose.set(i, totals_lose.get(i) + l.get(i));
					}
				}
			}
			else{
				win++;
				for(int i = 0; i < l.size() - 1; i++){
					if(!(i == 3 || i == 4)){
						totals_win.set(i, totals_win.get(i) + l.get(i));
					}
				}
			}
		}
		for(int i = 0; i < totals_win.size(); i++){
			totals_win.set(i, totals_win.get(i)/win);
			totals_lose.set(i, totals_lose.get(i)/loss);
		}
		//System.out.println("P(win) = " + (win/total_games) + "			P(loss) = " + (loss/total_games));
		//System.out.println(totals_win + "\n" + totals_lose + "\n");
		team_mean_win.add(totals_win);
		team_mean_lose.add(totals_lose);
		team_prob_win.add(win/total_games);
		team_prob_lose.add(loss/total_games);

		//variance
		for(List<Integer> l : team){
			//lose
			if(l.get(l.size() - 1) == 0){
				for(int i = 0; i < l.size() - 1; i++){
					if(!(i == 3 || i == 4)){
						var_lose.set(i, var_lose.get(i) + ((totals_lose.get(i) - l.get(i))*(totals_lose.get(i) - l.get(i))));
					}
				}
			}
			//win
			else{
				for(int i = 0; i < l.size() - 1; i++){
					if(!(i == 3 || i == 4)){
						var_win.set(i, var_win.get(i) + ((totals_win.get(i) - l.get(i))*(totals_win.get(i) - l.get(i))));
					}
				}
			}
		}
		for(int i = 0; i < var_win.size(); i++){
			var_win.set(i, var_win.get(i)/win);
			var_lose.set(i, var_lose.get(i)/loss);
		}
		//System.out.println("P(win) = " + (win/total_games) + "			P(loss) = " + (loss/total_games));
		//System.out.println(var_win + "\n" + var_lose + "\n");
		team_var_win.add(var_win);
		team_var_lose.add(var_lose);
	}

	public void get_team_avgs(List<List<Integer>> team){
		int counter = 0;
		int score = 0;
		int yards = 0;
		int turnovers = 0;
		int score_against = 0;
		int yards_against = 0;
		int turnovers_against = 0;
		for(List<Integer> l : team){
			counter++;
			score += l.get(6);
			score_against += l.get(7);
			yards += l.get(8);
			yards_against += l.get(9);
			turnovers += l.get(10);
			turnovers_against += l.get(11);
		}
		avg_score.add((double)score/counter);
		avg_score_against.add((double)score_against/counter);
		avg_yards.add((double)yards/counter);
		avg_yards_against.add((double)yards_against/counter);
		avg_turnovers.add((double)turnovers/counter);
		avg_turnovers_against.add((double)turnovers_against/counter);	
	}

	public void print_team(int team){
		List<List<Integer>> curr_team = teams.get(team);
		for(List<Integer> l : curr_team){
			System.out.println(l);
		}
	}

	public void list_files_in_folder(final File folder){
		for(final File entry : folder.listFiles()){
			if(entry.isDirectory()){
				list_files_in_folder(entry);
			}
			else{
				parse_file(entry);
			}
		}
	}

	public void list_files_in_folder_test(final File folder){
		for(final File entry : folder.listFiles()){
			if(entry.isDirectory()){
				list_files_in_folder_test(entry);
			}
			else{
				parse_file_test(entry);
			}
		}
	}

	/**
	 * INPUT FILE FORMAT
	 * first line: Week|Day|Date|Time|Winner/tie|5|Loser/tie|7|PtsW|PtsL|YdsW|TOW|YdsL|TOL
	 * next lines: data for each game each week of regular season
	 * i-th line:  ||Playoffs||||||||||
	 * next lines: data for each game each week of post season
	**/
	//list : week, year, day/time*, team, opposing team, home/away*, points scored, points against, yards gained, yards given up, turnovers, turnovers (opponent), win/loss
	public void parse_file(File f){
		try{
			Scanner s = new Scanner(f);
			String line = s.nextLine();
			Boolean playoffs = false;
			while(s.hasNextLine()){
				line = s.nextLine();
				if(line.contains("Playoffs")){
					playoffs = true;
				}
				else{
					String[] line_arr = line.split(",", -1);
					int[] temp_win = new int[13];
					int[] temp_lose = new int[13];
					temp_win[1] = Integer.parseInt(line_arr[2].substring(0,4));
					temp_lose[1] = Integer.parseInt(line_arr[2].substring(0,4));
					if(line_arr[1].equals("Thu")){
						temp_win[2] = 0;
						temp_lose[2] = 0;
					}
					else if(line_arr[1].equals("Mon")){
						temp_win[2] = 4;
						temp_lose[2] = 4;
					}
					else if(line_arr[1].equals("Sun")){
						if(line_arr[3].charAt(0) == '1'){
							temp_win[2] = 1;
							temp_lose[2] = 1;
						}
						else if(line_arr[3].charAt(0) == '4'){
							temp_win[2] = 2;
							temp_lose[2] = 2;
						}
						else if(line_arr[3].charAt(0) == '8'){
							temp_win[2] = 3;
							temp_lose[2] = 3;
						}
						else{
							temp_win[2] = 5;
							temp_lose[2] = 5;
						}
					}
					else{
						temp_win[2] = 5;
						temp_lose[2] = 5;
					}
					String[] win_team_arr = line_arr[4].split(" ", -1);
					String[] lose_team_arr = line_arr[6].split(" ", -1);
					String win_team = win_team_arr[win_team_arr.length - 1].toLowerCase();
					String lose_team = lose_team_arr[lose_team_arr.length - 1].toLowerCase();
					if(win_team.equals("49ers")){
						win_team = "fortyniners";
					}
					if(lose_team.equals("49ers")){
						lose_team = "fortyniners";
					}
					if(win_team.equals("redskins")){
						win_team = "football_team";
					}
					if(lose_team.equals("redskins")){
						lose_team = "football_team";
					}
					if(win_team.equals("team")){
						win_team = "football_team";
					}
					if(lose_team.equals("team")){
						lose_team = "football_team";
					}
					//System.out.println("win team = " + win_team + " ||| lose team = " + lose_team);
					temp_win[3] = teams_num.get(win_team);
					temp_win[4] = teams_num.get(lose_team);
					temp_lose[3] = teams_num.get(lose_team);
					temp_lose[4] = teams_num.get(win_team);
					if(line_arr[5].equals("")){
						temp_win[5] = 0;
						temp_lose[5] = 1;
					}
					else{
						temp_win[5] = 1;
						temp_lose[5] = 0;
					}
					temp_win[6] = Integer.parseInt(line_arr[8]);
					temp_lose[7] = 0-Integer.parseInt(line_arr[8]);
					temp_win[7] = 0-Integer.parseInt(line_arr[9]);
					temp_lose[6] = Integer.parseInt(line_arr[9]);
					temp_win[8] = Integer.parseInt(line_arr[10]);
					temp_lose[8] = Integer.parseInt(line_arr[12]);
					temp_win[9] = 0-Integer.parseInt(line_arr[12]);
					temp_lose[9] = 0-Integer.parseInt(line_arr[10]);
					temp_win[10] = 0-Integer.parseInt(line_arr[11]);
					temp_lose[10] = 0-Integer.parseInt(line_arr[13]);
					temp_win[11] = Integer.parseInt(line_arr[13]);
					temp_lose[11] = Integer.parseInt(line_arr[11]);
					temp_win[12] = 1;
					temp_lose[12] = 0;
					if(playoffs){
						int playoff_game = -1;
						if(line_arr[0].equals("WildCard")){
							playoff_game = 18;
						}
						else if(line_arr[0].equals("Division")){
							playoff_game = 19;
						}
						else if(line_arr[0].equals("ConfChamp")){
							playoff_game = 20;
						}
						else{
							//super bowl
							playoff_game = 21;
						}
						temp_win[0] = playoff_game;
						temp_lose[0] = playoff_game;
					}
					else{
						temp_win[0] = Integer.parseInt(line_arr[0]);
						temp_lose[0] = Integer.parseInt(line_arr[0]);
					}
					List<Integer> win_integer = new ArrayList<Integer>();
					List<Integer> lose_integer = new ArrayList<Integer>();
					for (int i = 0; i < temp_win.length; i++) {
						win_integer.add(temp_win[i]);
						lose_integer.add(temp_lose[i]);
					}
					List<List<Integer>> win = teams.get(temp_win[3]);
					List<List<Integer>> lose = teams.get(temp_win[4]);
					win.add(win_integer);
					lose.add(lose_integer);
					teams.put(temp_win[3], win);
					teams.put(temp_win[4], lose);
				}
			}
			
		}
		catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
		}
	}

	public void parse_file_test(File f){
		try{
			Scanner s = new Scanner(f);
			String line = s.nextLine();
			Boolean playoffs = false;
			while(s.hasNextLine()){
				line = s.nextLine();
				if(line.contains("Playoffs")){
					playoffs = true;
				}
				else{
					String[] line_arr = line.split(",", -1);
					double[] temp_win = new double[13];
					double[] temp_lose = new double[13];
					temp_win[1] = Integer.parseInt(line_arr[2].substring(0,4));
					temp_lose[1] = Integer.parseInt(line_arr[2].substring(0,4));
					if(line_arr[1].equals("Thu")){
						temp_win[2] = 0;
						temp_lose[2] = 0;
					}
					else if(line_arr[1].equals("Mon")){
						temp_win[2] = 4;
						temp_lose[2] = 4;
					}
					else if(line_arr[1].equals("Sun")){
						if(line_arr[3].charAt(0) == '1'){
							temp_win[2] = 1;
							temp_lose[2] = 1;
						}
						else if(line_arr[3].charAt(0) == '4'){
							temp_win[2] = 2;
							temp_lose[2] = 2;
						}
						else if(line_arr[3].charAt(0) == '8'){
							temp_win[2] = 3;
							temp_lose[2] = 3;
						}
						else{
							temp_win[2] = 5;
							temp_lose[2] = 5;
						}
					}
					else{
						temp_win[2] = 5;
						temp_lose[2] = 5;
					}
					String[] win_team_arr = line_arr[4].split(" ", -1);
					String[] lose_team_arr = line_arr[6].split(" ", -1);
					String win_team = win_team_arr[win_team_arr.length - 1].toLowerCase();
					String lose_team = lose_team_arr[lose_team_arr.length - 1].toLowerCase();
					if(win_team.equals("49ers")){
						win_team = "fortyniners";
					}
					if(lose_team.equals("49ers")){
						lose_team = "fortyniners";
					}
					if(win_team.equals("redskins")){
						win_team = "football_team";
					}
					if(lose_team.equals("redskins")){
						lose_team = "football_team";
					}
					if(win_team.equals("team")){
						win_team = "football_team";
					}
					if(lose_team.equals("team")){
						lose_team = "football_team";
					}
					//System.out.println("win team = " + win_team + " ||| lose team = " + lose_team);
					temp_win[3] = teams_num.get(win_team);
					temp_win[4] = teams_num.get(lose_team);
					temp_lose[3] = teams_num.get(lose_team);
					temp_lose[4] = teams_num.get(win_team);
					if(line_arr[5].equals("")){
						temp_win[5] = 0;
						temp_lose[5] = 1;
					}
					else{
						temp_win[5] = 1;
						temp_lose[5] = 0;
					}
					temp_win[6] = avg_score.get((int)temp_win[3]);
					temp_win[7] = 0-avg_score_against.get((int)temp_win[4]);
					temp_win[8] = avg_yards.get((int)temp_win[3]);
					temp_win[9] = 0-avg_yards_against.get((int)temp_win[4]);
					temp_win[10] = avg_turnovers.get((int)temp_win[3]);
					temp_win[11] = 0-avg_turnovers_against.get((int)temp_win[4]);

					temp_lose[6] = avg_score.get((int)temp_lose[3]);
					temp_lose[7] = 0-avg_score_against.get((int)temp_lose[4]);
					temp_lose[8] = avg_yards.get((int)temp_lose[3]);
					temp_lose[9] = 0-avg_yards_against.get((int)temp_lose[4]);
					temp_lose[10] = avg_turnovers.get((int)temp_lose[3]);
					temp_lose[11] = 0-avg_turnovers_against.get((int)temp_lose[4]);

					temp_win[12] = 1.0;
					temp_lose[12] = 0.0;

					if(playoffs){
						int playoff_game = -1;
						if(line_arr[0].equals("WildCard")){
							playoff_game = 18;
						}
						else if(line_arr[0].equals("Division")){
							playoff_game = 19;
						}
						else if(line_arr[0].equals("ConfChamp")){
							playoff_game = 20;
						}
						else{
							//super bowl
							playoff_game = 21;
						}
						temp_win[0] = playoff_game;
						temp_lose[0] = playoff_game;
					}
					else{
						temp_win[0] = Integer.parseInt(line_arr[0]);
						temp_lose[0] = Integer.parseInt(line_arr[0]);
					}
					List<Double> team_a = new ArrayList<Double>();
					List<Double> team_b = new ArrayList<Double>();
					for(int i = 0; i < temp_win.length; i++){
						team_a.add(0.0+temp_win[i]);
						team_b.add(0.0+temp_lose[i]);
					}
					test_games.add(team_a);
					test_games.add(team_b);
					sim_game(temp_win, temp_lose);
				}
			}
			
		}
		catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
		}
	}

	public void sim_game(double[] a, double[] b){
		global_total++;
		//TEAM A ***SHOULD*** WIN because i pass in the winning team as a
		List<Double> weight_a = team_weights.get((int)a[3]);
		List<Double> weight_b = team_weights.get((int)b[3]);
		double sum = -8000;
		if(weight_a.get(weight_a.size() - 1) > weight_b.get(weight_b.size() - 1)){
			List<Double> a_list = new ArrayList<Double>();
			for(int i = 0; i < a.length-1; i++){
				a_list.add(a[i]);
			}
			for(int j = 0; j < a_list.size() - 1; j++){
				sum += (weight_a.get(j)*a_list.get(j));
			}
			if(sum > 0){
				//team a wins, b loses
				global_correct++;
			}
		}
		else{
			List<Double> b_list = new ArrayList<Double>();
			for(int i = 0; i < b.length-1; i++){
				b_list.add(b[i]);
			}
			for(int j = 0; j < b_list.size() - 1; j++){
				sum += (weight_b.get(j)*b_list.get(j));
			}
			if(sum < 0){
				//team a wins, b loses
				global_correct++;
			}
		}
	}

	public void perceptron_train_teams(List<List<Integer>> team){
		List<Double> weights = new ArrayList<Double>();
		for(int i = 0; i < team.get(0).size(); i++){
			weights.add(1.0);
		}
		double learning_rate = .015;
		int bias = -8000;
		int loops = 12000;
		int sum = 0;
		for(int i = 0; i < loops; i++){
			int correct_counter = 0;
			int total_counter = 0;
			for(List<Integer> l : team){
				sum = bias;
				int outcome_expected = l.get(l.size() - 1);
				int outcome_calculated = 0;
				for(int j = 0; j < l.size() - 1; j++){
					sum += (weights.get(j)*l.get(j));
				}
				if(sum > 0){
					outcome_calculated = 1;
				}
				if(outcome_calculated == outcome_expected){
					correct_counter++;
				}
				total_counter++;
				double weight_change = 0;
				for(int j = 0; j < l.size() - 1; j++){
					weight_change = learning_rate * (outcome_expected - outcome_calculated) * weights.get(j);
					weights.set(j, weights.get(j) + weight_change);
					weight_change = 0;
				}
			}
			
			if(i == loops - 1){
				perceptron_accuracies.add(correct_counter + "/" + total_counter);
				weights.set(weights.size() - 1, (0.0 + correct_counter)/total_counter);
			}
		}
		team_weights.add(weights);
	}

}