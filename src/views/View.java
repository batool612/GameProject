package views;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class View extends Application{

	Game game;
	Hero h = new Fighter(null,1,1,1);
	public Button [][] m = new Button[15][15];
	int x;
	Label vaccine = new Label("Available Vaccine:       " + h.getVaccineInventory().size());
	Label supply = new Label("Available Supply:        " + h.getSupplyInventory().size());
	Label hppp = new Label("" + h.getCurrentHp() + "/" + h.getMaxHp());
	Label actionAvailable = new Label("Actions Available:        " + h.getActionsAvailable());
	ProgressBar hp = new ProgressBar(h.getCurrentHp()/h.getMaxHp());
	Assets assets = new Assets();
	MediaPlayer m1 = new MediaPlayer(Assets.back());
	//-----------------------------------------------------------------------------------------
	public void win() {
		Stage s = new Stage();
		 m1.stop();
		 MediaPlayer m2 = new MediaPlayer( Assets.win());
		 m2.play();
		 s.setTitle("Congratulations!!!");
		 Label l = new Label("                        You have won the game!!");
		 Label l2 = new Label();
		 l.setTextFill(Color.BLUE);
		 l2.setScaleX(10);
		 l2.setScaleY(10);
		 l2.setGraphic(Assets.win(50, 50));
		 BorderPane bo = new BorderPane();
		 l.setAlignment(Pos.BOTTOM_CENTER);
		 bo.setStyle("-fx-background-color: #FFFFFF;");
		 bo.setBottom(l);
		 bo.setCenter(l2);
		 l.setFont(new Font("Arial",20));
		 Scene sc = new Scene(bo,500,550);
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.show();
		 s.getIcons().add(new Image("win.png"));
		 s.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        System.exit(0);
			    }


			});
	}
	//------------------------------------------------------------------------------------------
	public void trap(int i) {
		Stage s = new Stage();
		Assets.dz().play();
		 s.setTitle("Trap...");
		 Label l = new Label("Oh no " + h.getName() + " has stepped on a trap and recieved " + i + " damage !!");
		 StackPane st = new StackPane();
		 l.setFont(new Font("Arial",20));
		 st.getChildren().add(l);
		 Scene sc = new Scene(st,1000,350);
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.getIcons().add(new Image("warn.png"));
		 s.show();
	}
	//-------------------------------------------------------------------------------------
	public void invalidTarget() {
	try {
		Stage s = new Stage();
		 s.setTitle("Invalid Target");
		 Label l = new Label("Please choose a valid target!");
		 StackPane st = new StackPane();
		 l.setFont(new Font("Arial",20));
		 st.getChildren().add(l);
		 Scene sc = new Scene(st,500,250);
		 MediaPlayer m = new MediaPlayer(Assets.err());
		 m.play();
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.getIcons().add(new Image("warn.png"));
		 s.show();
	}
	 catch(Exception e) {
		 
	 }
		 
	}
	//--------------------------------------------------------------------------------------
	public void invalidMovement() {
		try {
		Stage s = new Stage();
		 s.setTitle("Invalid move");
		 Label l = new Label("Please choose a valid cell to move to!");
		 StackPane st = new StackPane();
		 l.setFont(new Font("Arial",20));
		 st.getChildren().add(l);
		 Scene sc = new Scene(st,500,250);
		 MediaPlayer m = new MediaPlayer(Assets.err());
			m.play();
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.getIcons().add(new Image("warn.png"));
		 s.show();
	}
		catch(Exception e) {
			
		}
	}
	//-----------------------------------------------------------------------------------
	public void notEnoughActions() {
		try {
		 Stage s = new Stage();
		 s.setTitle("Not enough actions");
		 Label l = new Label("You do not have enough points!");
		 StackPane st = new StackPane();
		 l.setFont(new Font("Arial",20));
		 st.getChildren().add(l);
		 Scene sc = new Scene(st,500,250);
		 MediaPlayer m = new MediaPlayer(Assets.err());
			m.play();
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.getIcons().add(new Image("warn.png"));
		 s.show();
		} catch(Exception e) {
			
		}
	}
	//------------------------------------------------------------------------------------
	public void notEnoughResources() {
		try {
		Stage s = new Stage();
		 s.setTitle("Not enough resources");
		 Label l = new Label("You do not have enough resources!");
		 StackPane st = new StackPane();
		 l.setFont(new Font("Arial",20));
		 st.getChildren().add(l);
		 Scene sc = new Scene(st,500,250);
		 MediaPlayer m = new MediaPlayer(Assets.err());
			m.play();
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.getIcons().add(new Image("warn.png"));
		 s.show();
		}
		catch (Exception e) {
			
		}
	}
	//--------------------------------------------------------------------------------------------------------
	public void lose() {
		Stage s = new Stage();
		 m1.stop();
		 MediaPlayer m2 = new MediaPlayer( Assets.lose());
		 m2.play();
		 s.setTitle("Game over...");
		 Label l = new Label("                         You have lost the game...");
		 l.setTextFill(Color.RED);
		 Label l2 = new Label();
		 l2.setScaleX(10);
		 l2.setScaleY(10);
		 l2.setGraphic(Assets.lose(50, 50));
		 BorderPane bo = new BorderPane();
		 l.setAlignment(Pos.BOTTOM_CENTER);
		 bo.setStyle("-fx-background-color: #000000;");
		 bo.setBottom(l);
		 bo.setCenter(l2);
		 l.setFont(new Font("Arial",20));
		 Scene sc = new Scene(bo,500,550);
		 s.setScene(sc);
		 s.initModality(Modality.APPLICATION_MODAL);
		 s.getIcons().add(new Image("lose.png"));
		 s.show();
		 s.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        System.exit(0);
			    }


			});
	}
	//-------------------------------------------------------------------------------------------------------------
	public void updateInfo(Label hppp,Label vaccine,Label supply,Label actions,ProgressBar hp) {
			hppp.setText("" + h.getCurrentHp() + "/" + h.getMaxHp());
			vaccine.setText("Available Vaccine:       " + h.getVaccineInventory().size());
			supply.setText("Available Supply:        " + h.getSupplyInventory().size());
			actions.setText("Actions Available:        " + h.getActionsAvailable());
			double f =(double) h.getCurrentHp()/(double)h.getMaxHp();
			hp.setProgress(f);
	}
	//-------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
	launch(args);
	}

	//--------------------------------------------------------------------------------------------------------------
	public void error1() {
		 try {
		 Stage s = new Stage();
		 s.setTitle("Error");
		 Label l = new Label("Please select a hero before starting the game");
		 StackPane st = new StackPane();
		 l.setFont(new Font("Arial",20));
		 st.getChildren().add(l);
		 Scene sc = new Scene(st,500,250);
		 s.setScene(sc);
				MediaPlayer m = new MediaPlayer(Assets.err());
				m.play();
				 s.initModality(Modality.APPLICATION_MODAL);
				 s.getIcons().add(new Image("warn.png"));
				s.show();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	}
	//--------------------------------------------------------------------------------------------------------------
	@Override
	public void start(Stage start) throws Exception {
		start.setMaximized(true);
		Game.loadHeroes("Heroes.csv");
		start.setTitle("The Last Of Us Legacy");
		HBox heroes1 = new HBox(45);
		HBox heroes2 = new HBox(90);
		Button begin = new Button();
		BorderPane bo = new BorderPane(heroes2,begin,null,heroes1,null);
		heroes1.setTranslateX(35);
		heroes1.setTranslateY(-60);
		heroes2.setTranslateX(50);
		heroes2.setTranslateY(300);
		begin.setTranslateX(650);
		begin.setTranslateY(180);
		begin.setScaleX(2);
		begin.setScaleY(2);
		begin.setText("Start Game");
		begin.setStyle("-fx-background-color: #bf9000;-fx-border-width: 3.0;-fx-border-color: #a30000;");
		begin.setFont(new Font("IMPACT", 13));
		MediaPlayer mp = new MediaPlayer(Assets.intro());
		mp.setVolume(0.5);
		mp.play();
		mp.setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	            mp.seek(Duration.INDEFINITE);
	            mp.play();
	        }
	    }); 
		EventHandler<MouseEvent> e2 = new EventHandler<>() {

			@Override
			public void handle(MouseEvent e) {
				if(h.getName() == null) {
					error1();
				}
				else {
			    m1.play();
			    mp.stop();
				Game.startGame(h);
				updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				start.setMaximized(true);
				Stage st = new Stage();
				st.setTitle("The Last Of Us Legacy");
				start.hide();
				main(st);

			}
			}

		};
		begin.setOnMouseClicked(e2);
		//
		for (Hero k : Game.availableHeroes) {
			Button b = new Button();
			b.setScaleX(1.5);
			b.setScaleY(1.5);
			heroes2.getChildren().add(b);
			b.setStyle("-fx-background-color: #a30000;-fx-border-width: 3.0;-fx-border-color: #bf9000;");
		    Label l = new Label();
		    String type = "";
		    if(k instanceof Fighter)
		    	type = "Fighter";
		    else {
		    	if(k instanceof Medic)
		    		type = "Medic";
		    	else
		    		type = "Explorer";
		    }
		    l.setText("Name: " + k.getName() + "\n\n" +
		    		  "Type: " + type + "\n\n" +
		    		  "Max Hp: " + k.getMaxHp() + "\n\n" +
		    		  "Attack Damage: " + k.getAttackDmg() + "\n\n" +
		    		  "Max Action Points: " + k.getMaxActions());
		    l.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
			   l. setFont(new Font("Arial", 13));
			   l.setTextFill(Color.BLACK);
		   l.setStyle("-fx-border-color: #bf9000;-fx-border-width: 3.0;");
		   if(k.getName().equals("Joel Miller"))
			   b.setGraphic(Assets.joel(60,100));
			   else {
				   if(k.getName().equals("Ellie Williams"))
					   b.setGraphic(Assets.ellie(60,100));
				   else {
					   if(k.getName().equals("Tess"))
						   b.setGraphic(Assets.tess(60,100));
					   else {
						   if(k.getName().equals("Riley Abel"))
							   b.setGraphic(Assets.riley(60,100));
						   else {
							   if(k.getName().equals("Tommy Miller"))
								   b.setGraphic(Assets.tommy(60,100));
							   else {
								   if(k.getName().equals("Bill"))
									   b.setGraphic(Assets.bill(60,100));
								   else {
									   if(k.getName().equals("David"))
										   b.setGraphic(Assets.david(60,100));
									   else
										   b.setGraphic(Assets.henery(60,100));
								   }
							   }
						   }
					   }
				   }
			   }
		   //
		   EventHandler<MouseEvent> e1 = new EventHandler<>() {

				@Override
				public void handle(MouseEvent arg0) {
				    Assets.click().play();
				
					if(k instanceof Fighter)
				    	h = new Fighter(k.getName(),k.getMaxHp(),k.getAttackDmg(),k.getMaxActions());
				    else {
				    	if(k instanceof Medic)
				    		h = new Medic(k.getName(),k.getMaxHp(),k.getAttackDmg(),k.getMaxActions());
				    	else
				    		h = new Explorer(k.getName(),k.getMaxHp(),k.getAttackDmg(),k.getMaxActions());
				    }

				}
			};
			b.setOnMouseClicked(e1);
			//
		   heroes1.getChildren().add(l);

		}
		
		 BackgroundSize bSize = new BackgroundSize(bo.getMaxHeight(),bo.getMaxWidth(), true, true, true, true);

		    Background myBI = new Background(new BackgroundImage(new Image( Assets.ob(),0,0,true,true),
		            BackgroundRepeat.NO_REPEAT,
		            BackgroundRepeat.NO_REPEAT,
		            BackgroundPosition.CENTER,
		            bSize));

		bo.setBackground(myBI);
		Scene s = new Scene(bo,500,500);
		start.setScene(s);
		start.getIcons().add(new Image("icon.jpeg"));
		start.show();
		//////////////////////////////////////////////////////////////

	}
	//--------------------------------------------------------------------------------------------
	public void updateMap(Button[][]m) {
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				m[i][j].setGraphic(null);
				m[i][j].setStyle("-fx-background-color: #bababa;-fx-border-color: #bf9000;");
			}
		}
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				Button b = m[i][j];
				Cell c = Game.map[i][j];
				if(!Game.map[i][j].isVisible()) {
					b.setStyle("-fx-background-color: #000000;-fx-border-color: #bf9000;");
				}
				else {
				
				if(c instanceof CharacterCell) {
					if(((CharacterCell) c).getCharacter() instanceof Zombie) {
						b.setStyle("-fx-background-color: #780202;-fx-border-color: #bf9000;");
					if(Integer.parseInt("" + ((CharacterCell) c).getCharacter().getName().charAt(7)) %2 == 0)
						b.setGraphic(Assets.z1(24,24));
						else
							b.setGraphic(Assets.z2(24,24));

					}
					else {
						if(((CharacterCell) c).getCharacter() instanceof Fighter) {
							b.setStyle("-fx-background-color: #1e84ae;-fx-border-color: #bf9000;");
							if(((CharacterCell) c).getCharacter().getName().equals("Joel Miller")) {
								b.setGraphic(Assets.joel(24,24));

							}
							else
								if(((CharacterCell) c).getCharacter().getName().equals("David"))
									b.setGraphic(Assets.david(24,24));
						}
						else {
							if(((CharacterCell) c).getCharacter() instanceof Medic) {
								b.setStyle("-fx-background-color: #1e84ae;-fx-border-color: #bf9000;");
								if(((CharacterCell) c).getCharacter().getName().equals("Ellie Williams"))
									b.setGraphic(Assets.ellie(24,24));
								else
									if(((CharacterCell) c).getCharacter().getName().equals("Bill"))
										b.setGraphic(Assets.bill(24,24));
									else
											b.setGraphic(Assets.henery(24,24));
							}
							else {
								if(((CharacterCell) c).getCharacter() instanceof Explorer) {
									b.setStyle("-fx-background-color: #1e84ae;-fx-border-color: #bf9000;");
									if(((CharacterCell) c).getCharacter().getName().equals("Tess"))
										b.setGraphic(Assets.tess(24,24));
									else
										if(((CharacterCell) c).getCharacter().getName().equals("Riley Abel"))
											b.setGraphic(Assets.riley(24,24));
										else
												b.setGraphic(Assets.tommy(24,24));
							}
							}
						}
					}
				}
				else {
					if(c instanceof CollectibleCell) {
						b.setStyle("-fx-background-color: #4fee5d;-fx-border-color: #bf9000;");
						if(((CollectibleCell) c).getCollectible() instanceof Vaccine) {
							b.setGraphic(Assets.vaccine(24,24));
						}
						else {
							b.setGraphic(Assets.supply(24,24));
						}
					}
				}
			}}
		}
	}
//-------------------------------------------------------------------------------------------
	public void updateHeroes(VBox list) {
		list.getChildren().clear();
		Label l = new Label();
		l.setMinWidth(680);
		l.setMinHeight(20);
		l.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );
		l.setText("Current Heroes: ");
		l.setFont(new Font("Arial",13));
		list.getChildren().add(l);
		for (Hero k : Game.heroes) {
			Button b = new Button();
			b.setScaleX(2);
			b.setScaleY(2);
			b.setTranslateX(30);
			b.setTranslateY(13);
			if(k.getName().equals("Joel Miller"))
				   b.setGraphic(Assets.joel(20,20));
				   else {
					   if(k.getName().equals("Ellie Williams"))
						   b.setGraphic(Assets.ellie(20,20));
					   else {
						   if(k.getName().equals("Tess"))
							   b.setGraphic(Assets.tess(20,20));
						   else {
							   if(k.getName().equals("Riley Abel"))
								   b.setGraphic(Assets.riley(20,20));
							   else {
								   if(k.getName().equals("Tommy Miller"))
									   b.setGraphic(Assets.tommy(20,20));
								   else {
									   if(k.getName().equals("Bill"))
										   b.setGraphic(Assets.bill(20,20));
									   else {
										   if(k.getName().equals("David"))
											   b.setGraphic(Assets.david(20,20));
										   else
											   b.setGraphic(Assets.henery(20,20));
									   }
								   }
							   }
						   }
					   }}
			//
			EventHandler<MouseEvent> e2 = new EventHandler<>() {

				@Override
				public void handle(MouseEvent e) {
					h = k;
					 Assets.click().play();
					updateMap(m);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				}

			};
			b.setOnMouseClicked(e2);
			//
			Label t = new Label();
			String type = "";
			if(k instanceof Fighter)
		    	type = "Fighter";
		    else {
		    	if(k instanceof Medic)
		    		type = "Medic";
		    	else
		    		type = "Explorer";
		    }
			t.setText("Name: " + k.getName() + " || " +
		    		  "Type: " + type + " || " +
		    		  "Max Hp: " + k.getMaxHp() + " || " +
		    		  "Attack Damage: " + k.getAttackDmg() + " || " +
		    		  "Max Action Points: " + k.getMaxActions());
			 t.setStyle("-fx-background-color: #bf9000;-fx-border-width: 3.0;-fx-border-color: #a30000;");
			 t.setFont(new Font("Arial",13));
			 t.setTranslateX(100);
			 t.setTranslateY(-30);
			list.getChildren().addAll(b,t);
		}
	}
//------------------------------------------------------------------------------------------------------------
	public void  main(Stage st) {
		    m1.setVolume(0.1);
            m1.play();
            m1.setOnEndOfMedia(new Runnable() {
    	        @Override
    	        public void run() {
    	            m1.seek(Duration.INDEFINITE);
    	            m1.play();
    	        }
    	    }); 
		hp.setStyle("-fx-accent: #f44336;");
		BorderPane bo  = new BorderPane();
		GridPane g = new GridPane();
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				Button b = new Button();
				b.setMinWidth(42);
				b.setMinHeight(42);
				m[i][j] = b;


			}
		}
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				g.add(m[i][j], i, j);
				 Button b = m[i][j];
				 //
				EventHandler<MouseEvent> e1 = new EventHandler<>(){

					int y = GridPane.getRowIndex(b);
					int x = GridPane.getColumnIndex(b);
					@Override
					public void handle(MouseEvent e) {
						if(Game.map[x][y] instanceof CharacterCell) {
									if(((CharacterCell) Game.map[x][y]).getCharacter() == null) {
										invalidTarget();
									}
									else {
										h.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
										if(!h.checkDistance()) {
											h.setTarget(null);
											invalidTarget();
										}
										else
											Assets.click().play();
									}
								}

						else {
							invalidTarget();
						}
						}



				};
				b.setOnMouseClicked(e1);
				//
			}
		}
		updateMap(m);
		g.setStyle("-fx-background-color: #bf9000;");
		bo.setRight(g);

		g.setHgap(4);
		g.setVgap(4);
		g.setTranslateX(-215);
		g.setTranslateY(7);
		bo.setRight(g);
		//////////////
		VBox list = new VBox();
		Label l = new Label();
		l.setMinWidth(680);
		l.setMinHeight(20);
		l.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );
		l.setText("Current Heroes: ");
		l.setFont(new Font("Arial",13));
		list.getChildren().add(l);
		list.setSpacing(6);

		for (Hero k : Game.heroes) {
			Button b = new Button();
			b.setScaleX(2);
			b.setScaleY(2);
			b.setTranslateX(30);
			b.setTranslateY(13);

			if(k.getName().equals("Joel Miller"))
				   b.setGraphic(Assets.joel(20,20));
				   else {
					   if(k.getName().equals("Ellie Williams"))
						   b.setGraphic(Assets.ellie(20,20));
					   else {
						   if(k.getName().equals("Tess"))
							   b.setGraphic(Assets.tess(20,20));
						   else {
							   if(k.getName().equals("Riley Abel"))
								   b.setGraphic(Assets.riley(20,20));
							   else {
								   if(k.getName().equals("Tommy Miller"))
									   b.setGraphic(Assets.tommy(20,20));
								   else {
									   if(k.getName().equals("Bill"))
										   b.setGraphic(Assets.bill(20,20));
									   else {
										   if(k.getName().equals("David"))
											   b.setGraphic(Assets.david(20,20));
										   else
											   b.setGraphic(Assets.henery(20,20));
									   }
								   }
							   }
						   }
					   }}
			//
			EventHandler<MouseEvent> e2 = new EventHandler<>() {

				@Override
				public void handle(MouseEvent e) {
					h = k;
					 Assets.click().play();
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				}

			};
			b.setOnMouseClicked(e2);
			//
			Label t = new Label();
			String type = "";
			if(k instanceof Fighter)
		    	type = "Fighter";
		    else {
		    	if(k instanceof Medic)
		    		type = "Medic";
		    	else
		    		type = "Explorer";
		    }
			t.setText("Name: " + k.getName() + " || " +
		    		  "Type: " + type + " || " +
		    		  "Max Hp: " + k.getMaxHp() + " || " +
		    		  "Attack Damage: " + k.getAttackDmg() + " || " +
		    		  "Max Action Points: " + k.getMaxActions());
			 t.setStyle("-fx-background-color: #bf9000;-fx-border-width: 3.0;-fx-border-color: #a30000;");
			 t.setFont(new Font("Arial",13));
			 t.setTranslateX(100);
			 t.setTranslateY(-30);
			list.getChildren().addAll(b,t);
		}
		BackgroundImage b= new BackgroundImage(new Image( Assets.wood(),700,700,true,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false));

		list.setBackground(new Background(b));
		list.setMinWidth(680);
		bo.setLeft(list);
		//////////
		HBox menu = new HBox();
		Button attack = new Button("Attack");
		//
		EventHandler<MouseEvent> e3 = new EventHandler<>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					h.attack();
					Assets.att().play();
					if(h.getTarget()==null)
						Assets.dz().play();
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (NotEnoughActionsException e) {
					notEnoughActions();
				} catch (InvalidTargetException e) {
					invalidTarget();
				}

			}

		};
		attack.setOnMouseClicked(e3);
		//
		attack.setTranslateX(920);
		attack.setTranslateY(20);
		Button cure = new Button("Cure");
		//
		EventHandler<MouseEvent> e5 = new EventHandler<>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					h.cure();
					Assets.cure().play();
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (NoAvailableResourcesException e) {
					notEnoughResources();
				} catch (InvalidTargetException e) {
					invalidTarget();
				} catch (NotEnoughActionsException e) {
					notEnoughActions();
				}

			}

		};
		cure.setOnMouseClicked(e5);
		//
		cure.setTranslateX(900);
		cure.setTranslateY(20);
		Button useSpecial = new Button("Use Special");
		//
		EventHandler<MouseEvent> e6 = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					h.useSpecial();
					if(h instanceof Medic)
						Assets.heal().play();
					else
						if(h instanceof Explorer)
							Assets.exp().play();
						else
							Assets.att().play();
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (NoAvailableResourcesException e) {
					notEnoughResources();
				} catch (InvalidTargetException e) {
					invalidTarget();
				}

			}

		};
		useSpecial.setOnMouseClicked(e6);
		//
		useSpecial.setTranslateX(880);
		useSpecial.setTranslateY(20);
		
		Button endTurn = new Button("End Turn");
		//
		EventHandler<MouseEvent> e7 = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					if(Game.checkWin()) {
						win();
					}
					else
						if(Game.checkGameOver())
							lose();
						else {
					       Game.endTurn();
					       updateMap(m);
							updateHeroes(list);
							updateInfo(hppp,vaccine,supply,actionAvailable,hp);
						}
				} catch (NotEnoughActionsException e) {
					notEnoughActions();
				} catch (InvalidTargetException e) {
					invalidTarget();
				}

			}

		};
		endTurn.setOnMouseClicked(e7);
		//
		endTurn.setTranslateX(860);
		endTurn.setTranslateY(20);
		hp.setTranslateX(600);
		hp.setTranslateY(110);
		Label hpp = new Label("Current Hp: ");
		hpp.setTranslateX(-495);
		hpp.setTranslateY(110);
		hppp.setTranslateY(110);
		hppp.setTranslateX(-420);
		Button heart = new Button();
		heart.setTranslateX(-760);
		heart.setScaleX(1);
		heart.setScaleY(1);
		heart.setTranslateY(105);
		heart.setStyle( "-fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";-fx-background-color: #cc0000;" );
		actionAvailable.setTranslateX(280);
		actionAvailable.setTranslateY(80);
		supply.setTranslateY(169);
		supply.setTranslateX(40);
		vaccine.setTranslateY(140);
		vaccine.setTranslateX(-200);
		Button help = new Button();
		help.setScaleX(3);
		help.setScaleX(3);
		help.setTranslateX(150);
		help.setTranslateY(10);
		hpp.setTextFill(Color.LIGHTGRAY);
		hppp.setTextFill(Color.LIGHTGRAY);
		actionAvailable.setTextFill(Color.LIGHTGRAY);
		supply.setTextFill(Color.LIGHTGRAY);
		vaccine.setTextFill(Color.LIGHTGRAY);
        help.setGraphic(Assets.help(15,15));
        help.setMinWidth(15);
        help.setMinHeight(15);
        //
        EventHandler<MouseEvent> e8 = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Assets.hel().play();
				Stage s = new Stage();
				s.setTitle("Help");
				Label l = new Label();
				l.setText("* Each character has a number of actions points that allow you to move or attack.\n"
						+ "* To gain more heroes the hero needs to have at least one vaccine and cure a zombie.\n"
						+ "* Each type of hero has a special action that is unique to them and to use them the hero needs at least one supply:\n"
						+ "  Fighter:- attacks without using action points.\n"
						+ "  Medic:- heals any hero to full health.\n"
						+ "  Explorer:- reveals the entire map for one turn.\n"
						+ "* To win the game you need to have collected and used all vaccines and have atleast 5 heroes alive.\n"
						+ "* You will lose if there are no heroes alive or you have less than 5 heroes when you have collected and used all vaccines.");
				l.setFont(new Font("Ariel",15));
				Scene sc = new Scene(l,800,300);
				s.setScene(sc);
				 s.getIcons().add(new Image("help.png"));
				s.show();
			}

        };
        help.setOnMouseClicked(e8);
       //
		menu.setSpacing(40);
		menu.setTranslateY(-210);
		menu.getChildren().addAll(attack,cure,useSpecial,endTurn,hp,actionAvailable,supply,vaccine,help,hpp,hppp,heart);
		StackPane p = new StackPane();
		Button up = new Button();
		up.setTranslateX(140);
		up.setTranslateY(170);
	    up.setScaleX(2);
		up.setScaleY(2);
		Button down = new Button();
		down.setTranslateX(140);
		down.setTranslateY(310);
		down.setScaleX(2);
		down.setScaleY(2);
		down.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z'");
		down.setRotate(180);
		Button left = new Button();
		left.setTranslateX(70);
		left.setTranslateY(240);
	    left.setScaleX(2);
		left.setScaleY(2);
		left.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z'");
		left.setRotate(270);
		Button right = new Button();
		right.setTranslateX(210);
		right.setTranslateY(240);
	    right.setScaleX(2);
		right.setScaleY(2);
		right.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z'");
		right.setRotate(90);
		p.getChildren().addAll(up,down,left,right);
		p.setTranslateX(-350);
		p.setMaxWidth(0);
		p.setMaxHeight(0);
		p.setTranslateX(-415);
		p.setTranslateY(16);
		up.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z';-fx-background-color: #a30000;-fx-border-width: 2.0;-fx-border-color: #bf9000;");
		down.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z';-fx-background-color: #a30000;-fx-border-width: 2.0;-fx-border-color: #bf9000;");
		left.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z';-fx-background-color: #a30000;-fx-border-width: 2.0;-fx-border-color: #bf9000;");
		right.setStyle("-fx-shape: 'M150 0 L75 200 L225 200 z';-fx-background-color: #a30000;-fx-border-width: 2.0;-fx-border-color: #bf9000;");
		//
		EventHandler<MouseEvent> u = new EventHandler<>() {

			@Override
			public void handle(MouseEvent e) {
				try {
					boolean flag = false;
					int x = 0;
					try {
					if(Game.map[h.getLocation().x+1][h.getLocation().y] instanceof TrapCell) {
						flag = true;
						x = ((TrapCell) Game.map[h.getLocation().x+1][h.getLocation().y]).getTrapDamage();
					}}
					catch(ArrayIndexOutOfBoundsException a) {
						
					}
					Assets.click().play();
					h.move(Direction.UP);
					if(flag == true)
						trap(x);
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (MovementException e1) {
					invalidMovement();
				} catch (NotEnoughActionsException e1) {
					notEnoughActions();
				}

			}

		};
		EventHandler<MouseEvent> le = new EventHandler<>() {

			@Override
			public void handle(MouseEvent e) {
				try {
					boolean flag = false;
					int x = 0;
					try {
					if(Game.map[h.getLocation().x][h.getLocation().y-1] instanceof TrapCell) {
						flag = true;
						 x = ((TrapCell) Game.map[h.getLocation().x][h.getLocation().y-1]).getTrapDamage();
					}}
					catch(ArrayIndexOutOfBoundsException a) {
						
					}
					Assets.click().play();
					h.move(Direction.LEFT);
					if(flag == true)
						trap(x);
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (MovementException e1) {
					invalidMovement();
				} catch (NotEnoughActionsException e1) {
					notEnoughActions();
				}

			}

		};
		EventHandler<MouseEvent> r = new EventHandler<>() {

			@Override
			public void handle(MouseEvent e) {
				try {
					boolean flag = false;
					int x = 0;
					try {
					if(Game.map[h.getLocation().x][h.getLocation().y+1] instanceof TrapCell) {
						flag = true;
						x = ((TrapCell) Game.map[h.getLocation().x][h.getLocation().y+1]).getTrapDamage();
					}} 
					catch(ArrayIndexOutOfBoundsException a) {
						
					}
					Assets.click().play();
					h.move(Direction.RIGHT);
					if(flag == true)
					trap(x);
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (MovementException e1) {
					invalidMovement();
				} catch (NotEnoughActionsException e1) {
					notEnoughActions();
				}

			}

		};
		EventHandler<MouseEvent> d = new EventHandler<>() {

			@Override
			public void handle(MouseEvent e) {
				try {
					boolean flag = false;
					int x = 0;
					try {
					if(Game.map[h.getLocation().x-1][h.getLocation().y] instanceof TrapCell) {
						flag = true;
						 x = ((TrapCell) Game.map[h.getLocation().x-1][h.getLocation().y]).getTrapDamage();
					}}
					catch(ArrayIndexOutOfBoundsException a) {
											}
					Assets.click().play();
					h.move(Direction.DOWN);
					if(flag == true)
					trap(x);
					updateMap(m);
					updateHeroes(list);
					updateInfo(hppp,vaccine,supply,actionAvailable,hp);
				} catch (MovementException e1) {
					invalidMovement();
				} catch (NotEnoughActionsException e1) {
					notEnoughActions();
				}

			}

		};
//
		attack.setMinWidth(90);
		cure.setMinWidth(50);
		cure.setFont(new Font("IMPACT",14));
		useSpecial.setFont(new Font("IMPACT",14));
		endTurn.setFont(new Font("IMPACT",14));
		useSpecial.setMinWidth(90);
		endTurn.setMinWidth(80);
		attack.setFont(new Font("IMPACT",14));
		actionAvailable.setFont(new Font("STENCIL",13));
		actionAvailable.setMinWidth(200);
		hp.setMinWidth(150);
		hpp.setFont(new Font("STENCIL",13));
		hpp.setMinWidth(180);
		hppp.setFont(new Font("STENCIL",13));
		hppp.setMinWidth(100);
		vaccine.setFont(new Font("STENCIL",13));
		vaccine.setMinWidth(200);
		supply.setFont(new Font("STENCIL",13));
		supply.setMinWidth(200);
		//
		up.setOnMouseClicked(le);
		down.setOnMouseClicked(r);
		left.setOnMouseClicked(d);
		right.setOnMouseClicked(u);
		//
		bo.setBottom(menu);
		//
		Rectangle rec = new Rectangle();
		rec.setFill(Color.WHITE);
		rec.setArcHeight(35);
	    rec.setArcWidth(35);
	    rec.setTranslateX(0);
	    rec.setX(30);
	    rec.setY(30);
	    rec.setWidth(200);
	    rec.setHeight(200);
	    rec.setTranslateX(-540);
	    rec.setTranslateY(27);
	    rec.setFill(new ImagePattern(new Image(Assets.move(),50,0,true,true)));
	    menu.getChildren().add(rec);
	    //
	    menu.setMinWidth(1580);
	    menu.setTranslateX(-900);
	    menu.setMinHeight(230);
	    bo.setBottom(menu);

	    bo.setCenter(p);

	    BackgroundImage myBI= new BackgroundImage(new Image( Assets.steel(),50,0,true,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false));

		menu.setBackground(new Background(myBI));

		Scene s = new Scene(bo);
		st.setMaximized(true);
        st.setScene(s);
        st.getIcons().add(new Image("icon.jpeg"));
        st.show();
	}

}
