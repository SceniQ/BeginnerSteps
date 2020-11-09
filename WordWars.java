/*
	Chapter  	: WordWars - Application Project
	Programmer	: Tebogo Assagaai
	Filename	: WordWars.java
	Start Date  : 25 October 2020 [06:25]
	End Date	: 
	Purpose		: To provide a better understanding of Java abilities for a scrabble-like game (Using ArrayList specifically)
*/

// Import statements
import javax.swing.*;
import java.util.*;


public class WordWars{
	
	private static ArrayList<String> stringData = new ArrayList<>();
	private static ArrayList<Integer> primData = new ArrayList<>();
	private static ArrayList<Character> alphas = new ArrayList<>();
	private static ArrayList<Character> userAlphas = new ArrayList<>();
	
	public static void main (String... wordz){
	
	// ================================================== InitializingData
		primData.add(0,0); // get(0) - no. of rounds player two has won																// get(0)
		primData.add(1,0); // get(1) - no. of rounds player one has won
		stringData.add(0," ");
		stringData.add(1," ");
		
	// ================================================================================================================================================ Welcome note
		JOptionPane.showMessageDialog(null,"*** Welcome To WORD WARS: The Game ***","Intro...",JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null,"GAME OBJECTIVE:"+"\n"+
										   "Players will be presented with alphabets. Each player has to create"+"\n"+
										   "a word with the letters in the alphabet list. When a player enters a word,"+"\n"+
										   "the letters of that word are removed from the list. ONLY VOWELS REMAIN. Before"+"\n"+
										   "a word can be accepted, BOTH players must agree if the word entered is valid or not"+
										   "\n"+"\n"+
										   "PS: Make sure you factually verify the words while playing");
		
		primData.add(2,JOptionPane.showConfirmDialog(null,"*** Would you like to play? ***","Intro...",JOptionPane.YES_NO_OPTION));		// get(2)
		
		if(primData.get(2) == JOptionPane.NO_OPTION)
			System.exit(0);
		
	// =============================================================================================================================================== Player name input
		stringData.add(2,JOptionPane.showInputDialog(null, "Please enter Player 1's name: ","Player 1",JOptionPane.INFORMATION_MESSAGE)); // get(2)
		stringData.add(3,JOptionPane.showInputDialog(null, "Please enter Player 2's name: ","Player 2",JOptionPane.INFORMATION_MESSAGE));	// get(3)
	
	// =============================================================================================================================================== Starting the game
		JOptionPane.showMessageDialog(null, "*** LET'S PLAY WORD WARS ***"+"\n"+
											"        [ "+stringData.get(2) +" - VS - "+stringData.get(3)+" ]");
		
	// =============================================================================================================================================== Popuplating alphabets
		for(char x = 'a'; x <= 'z'; x++)
			alphas.add(x);
		
	//	============================================================================================================================================== PLAYER MODE
	
outter: while(true){ 
		
			// ======================================================================================================================================= PLAYER ONE's TURN
			inner0: while(true){
			
				stringData.add(4,JOptionPane.showInputDialog(null, stringData.get(2)+"'s"+" turn: Create a word using the letters below: "+"\n"+"***"+"\n"+
								"letters left: "+alphas+"\n"+"***","Game Play",JOptionPane.INFORMATION_MESSAGE)); 				
			
				if(stringData.get(4).equals("???"))
					break outter;
				else if(stringData.get(4).equals("***")){
						primData.set(2, JOptionPane.showConfirmDialog(null, stringData.get(2)+","+"\nAre you sure you want to pass this round to "+stringData.get(3)+" ?","???",
												  JOptionPane.YES_NO_OPTION));
								if(primData.get(2) == JOptionPane.YES_OPTION){ primData.add(3,0); break inner0;} else{ continue inner0;}
				}			
			
				else{
				
					stringData.add(4, stringData.get(4).toLowerCase());
					primData.add(3,JOptionPane.showConfirmDialog(null, "Do both players agree on the word: '"+ stringData.get(4) + "' ?","",JOptionPane.YES_NO_OPTION));		
	
					// ======================================================================================================================================= GAME control
					inner1:	while(true){
						if(primData.get(3) == JOptionPane.YES_OPTION){
							for(int x = 0; x < stringData.get(4).length(); x++){
					
									userAlphas.add(0, stringData.get(4).charAt(x));
					
								if(userAlphas.get(0) == 'a'|| userAlphas.get(0) == 'e'|| userAlphas.get(0) == 'i' || userAlphas.get(0) == 'o' || userAlphas.get(0) == 'u')
									continue;
					
								else{alphas.remove(userAlphas.get(0));}
							}	
								primData.set(0, (primData.get(0) + 1));
								break inner0;
						} else{JOptionPane.showMessageDialog(null, stringData.get(2)+ ", please enter another word/ type in: ' *** '"+
															" to pass the round to "+stringData.get(3)); continue inner0;}
						
					}
				
				}
				
			}

			// ======================================================================================================================================= PLAYER TWO's TURN
			inner2:	while(true){
			
				stringData.add(5,JOptionPane.showInputDialog(null, stringData.get(3)+"'s"+" turn: Create a word using the letters below: "+"\n"+"***"+"\n"+
										"letters left: "+alphas+"\n"+"***","Game Play",JOptionPane.INFORMATION_MESSAGE));

				if(stringData.get(5).equals("???")){
					break outter;
				}
				else if(stringData.get(5).equals("***")){
					primData.set(2, JOptionPane.showConfirmDialog(null, stringData.get(3)+","+"\nAre you sure you want to pass this round to "+stringData.get(2)+" ?","???",
												  JOptionPane.YES_NO_OPTION));
								if(primData.get(2) == JOptionPane.YES_OPTION){ break inner2;} else{continue inner2;}
				}
				
				else{
				
					stringData.add(5, stringData.get(5).toLowerCase());
					primData.add(4,JOptionPane.showConfirmDialog(null, "Do both players agree on the word: '"+ stringData.get(5) + "' ?","",JOptionPane.YES_NO_OPTION));
				
					// ======================================================================================================================================= GAME control
				inner3: while(true){
						if(primData.get(4) == JOptionPane.YES_OPTION){
							for(int x = 0; x < stringData.get(5).length(); x++){
						
									userAlphas.add(0, stringData.get(5).charAt(x));
								
								if(userAlphas.get(0) == 'a'|| userAlphas.get(0) == 'e'|| userAlphas.get(0) == 'i' || userAlphas.get(0) == 'o' || userAlphas.get(0) == 'u')
									continue;
							
								else{alphas.remove(userAlphas.get(0));}
							}	
						
								primData.set(1, (primData.get(1) + 1));
								break inner2;
						} else{JOptionPane.showMessageDialog(null, stringData.get(3)+ ", please enter another word/ type in: ' *** '"+
															" to pass the round to "+stringData.get(2)); continue inner2;}
					}		
				
				}
			}
		}
		
	//	============================================================================================================================================== GAME RESULTS
	
		if(primData.get(0) > primData.get(1)){
			JOptionPane.showMessageDialog(null, "GAME RESULTS: "+"\n"+
												stringData.get(2)+":   ["+ primData.get(0)+"]  word(s) created"+"\n"+
												stringData.get(3)+":   ["+ primData.get(1)+"]  word(s) created"+"\n"+"\n"+
												"  ** "+stringData.get(2)+ " is the winner **","RESULTS",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(primData.get(0) < primData.get(1)){
			JOptionPane.showMessageDialog(null, "GAME RESULTS: "+"\n"+
												stringData.get(2)+":   ["+ primData.get(0)+"]  word(s) created"+"\n"+
												stringData.get(3)+":   ["+ primData.get(1)+"]  word(s) created"+"\n"+"\n"+
												"  ** "+stringData.get(3)+ " is the winner **","RESULTS",JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "GAME RESULTS: "+"\n"+
												stringData.get(2)+":   ["+ primData.get(0)+"]  word(s) created"+"\n"+
												stringData.get(3)+":   ["+ primData.get(1)+"]  word(s) created"+"\n"+"\n"+
												"  ** The Game is a Tie **"+"\n"+"Play again to settle the score","RESULTS",JOptionPane.INFORMATION_MESSAGE);
		}
		
		JOptionPane.showMessageDialog(null, "Thank you for playing WORD WARS. We hope you have learned new words while playing the game."+"\n"+
											"Feel free to play again","",JOptionPane.INFORMATION_MESSAGE);
			
	}
	
}




