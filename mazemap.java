package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class mazemap extends Application {
	ArrayList<Mazepoint> list;
	ArrayList<Mazepoint> list2;
	ArrayList<Mazepoint> list3;
	ArrayList<Mazepoint> list4;
	ArrayList<Mazepoint> list6;
	ArrayList<Mazepoint> list7;
	ArrayList<Mazepoint>temp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Button button1 = new Button("自动生成");
		button1.setPrefSize(100, 50);
		Button button2 = new Button("读取文件");
		button2.setPrefSize(100, 50);
		
		
		
button2.setOnAction(new EventHandler<ActionEvent>() {
//打开文件界面
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		Button buttondk =new Button("打开并生成地图");
		buttondk.setLayoutX(20);
		buttondk.setLayoutY(50);
		buttondk.setOnAction(new EventHandler<ActionEvent>() {
		
			
		//把文件中的数据读取出来存入节点数组
	ArrayList<Mazepoint> readData(File file) throws IOException {
		ArrayList<Mazepoint>mazefile=new ArrayList<>();
		StringBuilder result = new StringBuilder();
        try{ 
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            int n=0;
            while((s = br.readLine())!=null) {
            	n++;
            }
            br.close();
            br = new BufferedReader(new FileReader(file));
            int i=0,j=0;
            while((s = br.readLine())!=null) {
            	Mazepoint mazepoint = new Mazepoint(i, j);
            	mazepoint.setX(i);
            	mazepoint.setY(j);
            	mazepoint.setUp(Integer.valueOf(s.charAt(0)));
            	mazepoint.setDown(Integer.valueOf(s.charAt(1)));
            	mazepoint.setLeft(Integer.valueOf(s.charAt(2)));
            	mazepoint.setRight(Integer.valueOf(s.charAt(3)));
            	i++;
            	if(i%((int)Math.sqrt(n))==0)
            	{
            		j++;
            	}
            	mazefile.add(mazepoint);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
		return mazefile;
	}
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				//文件选择窗口
				Stage mainStage = null;
				FileChooser chooser=new FileChooser();
				File selectedFile = chooser.showOpenDialog(mainStage);
			
				try {
				temp=readData(selectedFile);	
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Pane panewj2 = new Pane();
				Button buttonxl2 = new Button("最短路径");
				Button buttonzd2= new Button("单步寻路");
				Button buttonbl2= new Button("遍历地图");
				
				buttonxl2.setLayoutX(720);
				buttonxl2.setLayoutY(60);
				buttonzd2.setLayoutX(720);
				buttonzd2.setLayoutY(620);
				buttonbl2.setLayoutX(720);
				buttonbl2.setLayoutY(350);
				panewj2.getChildren().addAll(buttonxl2, buttonzd2,buttonbl2);
				Scene scenewj2 = new Scene(panewj2, 800, 700);
				//按照读取数据画地图
				int n=(int)(Math.sqrt(temp.size()));
				for(int i=0;i<temp.size();i++)
				{
					double x=temp.get(i).getX()%n;
					double y=temp.get(i).getY();
					if(temp.get(i).getDown()==48)
					{
						Line lined = new Line();
						lined.setStroke(Color.BLACK);
						lined.setStartX(((scenewj2.getWidth() - 100) / n) * x);
						lined.setEndX(((scenewj2.getWidth() - 100) / n) * (x+1));
						lined.setStartY((scenewj2.getHeight() / n) * (y+1));
						lined.setEndY((scenewj2.getHeight() / n) * (y+1));
						panewj2.getChildren().add(lined);
					}
					if(temp.get(i).getUp()==48)
					{
						Line line = new Line();
						line.setStroke(Color.BLACK);
						line.setStartX(((scenewj2.getWidth() - 100) / n) * x);
						line.setEndX(((scenewj2.getWidth() - 100) /n) * (x+1));
						line.setStartY((scenewj2.getHeight() / n) *y);
						line.setEndY((scenewj2.getHeight() / n) *y);
						panewj2.getChildren().add(line);
					}
					if(temp.get(i).getLeft()==48)
					{
						Line line = new Line();
						line.setStroke(Color.BLACK);
						line.setStartX((scenewj2.getWidth() / temp.size()) *x);
						line.setEndX((scenewj2.getWidth() / temp.size()) *x);
						line.setStartY((scenewj2.getHeight() / temp.size()) * (y));
						line.setEndY((scenewj2.getHeight() / temp.size()) * (y+1));
						panewj2.getChildren().add(line);
					}
					if (temp.get(i).getRight()==48) {
						Line line = new Line();
						line.setStroke(Color.BLACK);
						line.setStartX(((scenewj2.getWidth() - 100) / n) * (x + 1));
						line.setEndX(((scenewj2.getWidth() - 100) / n) * (x + 1));
						line.setStartY((scenewj2.getHeight() / n) * y);
						line.setEndY((scenewj2.getHeight() / n) * (y + 1));
						panewj2.getChildren().add(line);
					}
				
				
				}
				buttonbl2.setOnAction(new EventHandler<ActionEvent>() {
					
					ArrayList<Mazepoint>list6=temp;
					Circle getCenter(double x, double y){
						x%=n;
						double gridSize = (scenewj2.getWidth() - 100) / (n);
						x *= gridSize;
						y *= gridSize;
						
						double radius = gridSize/4;
						double cx = x + gridSize/2;
						double cy = y + gridSize/2;
						Circle circle = new Circle(cx, cy, radius);
						circle.setFill(Color.YELLOW);
						return circle;
					}
					
					int width = n;
					int height = n;
					
					int startx = 0;
					int starty = 0;
					
					Mazepoint getPoint(ArrayList<Mazepoint> list, int x, int y){
						return list6.get(x + y*height);
					}
					//遍历文件地图
					boolean findwjPath(int x, int y)
					{
					    boolean finish = false;
					    //已访问过，退出
					    if(getPoint(list6, x, y).isVisted()){
					    	return finish;
					    }
					   
					    getPoint(list6, x, y).setVisted(true) ;
					    //如果迷宫没有走结束则将搜索所在位置的右、下、左、上四个方向是否能够走通
					    if (!finish && x + 1 < width && getPoint(list6, x, y).getRight() == 49){		//右
					        if (findwjPath(x + 1, y))
					            return true;
					    }
					    if (!finish && y + 1 < height && getPoint(list6, x, y).getDown() == 49){		//下
					        if (findwjPath(x, y + 1))
					            return true;
					    }
					    if (!finish && x - 1 >= 0 && getPoint(list6, x, y).getLeft() == 49){		//左
					        if (findwjPath(x - 1, y))
					            return true;
					    }
					    
					    if (!finish && y - 1 >= 0 && getPoint(list6, x, y).getUp() == 49){	//上
					        if (findwjPath(x, y - 1))
					            return true;
					    }
					    
					    
					    if (!finish){//四周都不通
					    	getPoint(list6, x, y).setOnPath(true);
					    }
					    return finish;
					}
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						
						for(int i=0;i<list6.size();i++)
						{
							list6.get(i).setVisted(false);
						}
						
						findwjPath(startx,starty);
						for (int i=0;i<list6.size();i++) {
							if(list6.get(i).getOnPath()){
								Circle circle = getCenter(list6.get(i).getX(),list6.get(i).getY());
								panewj2.getChildren().add(circle);
							}
						}
					}
				});
				buttonxl2.setOnAction(new EventHandler<ActionEvent>() {
					ArrayList<Mazepoint>list7=temp;
					Circle getCenter(double x, double y){
						x%=n;
						double gridSize = (scenewj2.getWidth() - 100) / (n);
						x *= gridSize;
						y *= gridSize;
						
						double radius = gridSize/4;
						double cx = x + gridSize/2;
						double cy = y + gridSize/2;
						Circle circle = new Circle(cx, cy, radius);
						circle.setFill(Color.GREEN);
						return circle;
					}
					
					int width = n;
					int height = n;
					
					int startx = 0;
					int starty = 0;
					int endx = n- 1;
					int endy = n- 1;
					
					Mazepoint getPoint(ArrayList<Mazepoint> list, int x, int y){
						return list7.get(x + y*height);
					}
					//走读取文件的迷宫
					boolean findwjPath(int x, int y)
					{
					    boolean finish = false;
					    //已访问过，退出
					    if(getPoint(list7, x, y).isVisted()){
					    	return finish;
					    }
					    
					    //假设能够走通
					    getPoint(list7, x, y).setOnPath(true);
					    //如果到达重点则将end置为0表示迷宫已经走结束
					    if (x == endx && y == endy){
					        finish = true;
					    }
					    getPoint(list7, x, y).setVisted(true) ;
					    //如果迷宫没有走结束则将搜索所在位置的右、下、左、上四个方向是否能够走通
					    if (!finish && x + 1 < width && getPoint(list7, x, y).getRight() == 49){		//右
					        if (findwjPath(x + 1, y))
					            return true;
					    }
					    if (!finish && y + 1 < height && getPoint(list7, x, y).getDown() == 49){		//下
					        if (findwjPath(x, y + 1))
					            return true;
					    }
					    if (!finish && x - 1 >= 0 && getPoint(list7, x, y).getLeft() == 49){		//左
					        if (findwjPath(x - 1, y))
					            return true;
					    }
					    
					    if (!finish && y - 1 >= 0 && getPoint(list7, x, y).getUp() == 49){	//上
					        if (findwjPath(x, y - 1))
					            return true;
					    }
					    
					    
					    if (!finish){//四周都不通
					    	getPoint(list7, x, y).setOnPath(false);
					    }

					    return finish;
					}
					@Override
					public void handle(ActionEvent event) {
						
						// TODO Auto-generated method stub
						for(int i=0;i<list7.size();i++)
						{
							list7.get(i).setVisted(false);
						}
						
						findwjPath(startx,starty);
						for (int i=0;i<list7.size();i++) {
							if(list7.get(i).getOnPath()){
								Circle circle = getCenter(list7.get(i).getX(),list7.get(i).getY());
								panewj2.getChildren().add(circle);
							}
						}
					}
				});

				Stage stagewj2=new Stage();
				stagewj2.setScene(scenewj2);
				stagewj2.show();
			}
		});
		Pane panewj=new Pane();
		panewj.getChildren().addAll(buttondk);
		Scene scenewj=new Scene(panewj,150,100);
		Stage stagewj =new Stage();
		stagewj.setScene(scenewj);
		stagewj.show();
		
		
		
		
	}
});
							
		
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Scanner scanner = new Scanner(System.in);

				BorderPane borderPane1 = new BorderPane();
				TextField field1 = new TextField("横排节点数");
				TextField field2 = new TextField("纵排节点数");
				Button buttons = new Button("生成");
				buttons.setPrefSize(130, 30);
				buttons.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						int w = Integer.valueOf(field1.getText());
						int h = Integer.valueOf(field2.getText());
						mazemap maze1 = new mazemap();
						mazeModel maze2 = new mazeModel(w, h);
						list = maze2.getMaze();
						list2=maze2.getMaze();
						list4=maze2.getMaze();
						Pane pane = new Pane();
						Scene scene = new Scene(pane, 800, 700);
						Button buttonxl = new Button("最短路径");
						Button buttonzd = new Button("单步寻路");
						Button buttonbl= new Button("遍历地图");
						buttonxl.setLayoutX(720);
						buttonxl.setLayoutY(60);
						buttonzd.setLayoutX(720);
						buttonzd.setLayoutY(620);
						buttonbl.setLayoutX(720);
						buttonbl.setLayoutY(350);
						pane.getChildren().addAll(buttonxl, buttonzd,buttonbl);

						for (int i = 0; i < list.size(); i++) {
							double x = list.get(i).getX();
							double y = list.get(i).getY();
							if (list.get(i).getDown() == 0) {
								Line line = new Line();
								line.setStroke(Color.BLACK);
								line.setStartX(((scene.getWidth() - 100) / (h)) * x);
								line.setEndX(((scene.getWidth() - 100) / (h)) * (x + 1));
								line.setStartY((scene.getHeight() / (h)) * (y + 1));
								line.setEndY((scene.getHeight() / (h)) * (y + 1));
								pane.getChildren().add(line);
							}
							if (list.get(i).getUp()== 0) {
								Line line = new Line();
								line.setStroke(Color.BLACK);
								line.setStartX(((scene.getWidth() - 100) / (h)) * x);
								line.setEndX(((scene.getWidth() - 100) / (h)) * (x + 1));
								line.setStartY((scene.getHeight() / (h)) * (y));
								line.setEndY((scene.getHeight() / (h)) * (y));
								pane.getChildren().add(line);
							}
							if (list.get(i).getLeft() == 0) {
								Line line = new Line();
								line.setStroke(Color.BLACK);
								line.setStartX((scene.getWidth() / (w * h)) * x);
								line.setEndX((scene.getWidth() / (w * h)) * (x));
								line.setStartY((scene.getHeight() / (w * h)) * (y));
								line.setEndY((scene.getHeight() / (w * h)) * (y + 1));
								pane.getChildren().add(line);
							}
							if (list.get(i).getRight()== 0) {
								Line line = new Line();
								line.setStroke(Color.BLACK);
								line.setStartX(((scene.getWidth() - 100) / (h)) * (x + 1));
								line.setEndX(((scene.getWidth() - 100) / (h)) * (x + 1));
								line.setStartY((scene.getHeight() / (h)) * (y));
								line.setEndY((scene.getHeight() / (h)) * (y + 1));
								pane.getChildren().add(line);
							}

						}
						buttonxl.setOnAction(new EventHandler<ActionEvent>() {

							Circle getCenter(double x, double y){
								double gridSize = (scene.getWidth() - 100) / (h);
								x *= gridSize;
								y *= gridSize;
								
								double radius = gridSize/4;
								double cx = x + gridSize/2;
								double cy = y + gridSize/2;
								Circle circle = new Circle(cx, cy, radius);
								circle.setFill(Color.GREEN);
								return circle;
							}
							
							int width = maze2.getWideth();
							int height = maze2.getHeight();
							
							int startx = 0;
							int starty = 0;
							int endx = maze2.getWideth() - 1;
							int endy = maze2.getWideth()- 1;
							
							Mazepoint getPoint(ArrayList<Mazepoint> list, int x, int y){
								return list.get(x + y*height);
							}
							
							//走迷宫
							boolean findPath(int x, int y)
							{
							    boolean finish = false;
							    

							    //已访问过，退出
							    if(getPoint(list, x, y).isVisted()){
							    	return finish;
							    }
							    
							    //假设能够走通
							    getPoint(list, x, y).setOnPath(true);

							    //如果到达重点则将end置为0表示迷宫已经走结束
							    if (x == endx && y == endy){
							        finish = true;
							    }
							    
							    getPoint(list, x, y).setVisted(true) ;
							    
							    //如果迷宫没有走结束则将搜索所在位置的右、下、左、上四个方向是否能够走通
							    if (!finish && x + 1 < width && getPoint(list, x, y).getRight() == 1){		//右
							        if (findPath(x + 1, y))
							            return true;
							    }
							    if (!finish && y + 1 < height && getPoint(list, x, y).getDown() == 1){		//下
							        if (findPath(x, y + 1))
							            return true;
							    }
							    if (!finish && x - 1 >= 0 && getPoint(list, x, y).getLeft() == 1){		//左
							        if (findPath(x - 1, y))
							            return true;
							    }
							    
							    if (!finish && y - 1 >= 0 && getPoint(list, x, y).getUp() == 1){	//上
							        if (findPath(x, y - 1))
							            return true;
							    }
							    
							    
							    if (!finish){//四周都不通
							    	getPoint(list, x, y).setOnPath(false);
							    }

							    return finish;
							}
							
							@Override
							public void handle(ActionEvent event) {
								for (Mazepoint mazepoint : list) {
									mazepoint.setVisted(false);
								}
								
								findPath(startx,starty);
								for (Mazepoint mazepoint : list) {
									if(mazepoint.getOnPath()){
										Circle circle = getCenter(mazepoint.getX(),mazepoint.getY());
										pane.getChildren().add(circle);
									}
								}
							}
						});
						buttonbl.setOnAction(new EventHandler<ActionEvent>() {
							Circle getCenter(double x, double y){
								double gridSize = (scene.getWidth() - 100) / (h);
								x *= gridSize;
								y *= gridSize;
								
								double radius = gridSize/4;
								double cx = x + gridSize/2;
								double cy = y + gridSize/2;
								Circle circle = new Circle(cx, cy, radius);
								circle.setFill(Color.YELLOW);
								return circle;	
								
							}
							int width = maze2.getWideth();
							int height = maze2.getHeight();
							
							int startx = 0;
							int starty = 0;
							
							Mazepoint getPoint(ArrayList<Mazepoint> list2, int x, int y){
								return list2.get(x + y*height);
							}
							//遍历迷宫
							boolean goPath(int x,int y) {
									boolean finish=false;
									
									//如果已经访问，则退出	
									if(getPoint(list2, x, y).isVisted()) {
										return false;
									}
									
									
									 getPoint(list2, x, y).setVisted(true) ;
									
									 //判断上下左右
									 if (!finish && x + 1 < width && getPoint(list2, x, y).getRight() == 1){		//右
									        if (goPath(x + 1, y))
									            return true;
									    }
									    if (!finish && y + 1 < height && getPoint(list2, x, y).getDown() == 1){		//下
									        if (goPath(x, y + 1))
									            return true;
									    }
									    if (!finish && x - 1 >= 0 && getPoint(list2, x, y).getLeft() == 1){		//左
									        if (goPath(x - 1, y))
									            return true;
									    }
									    
									    if (!finish && y - 1 >= 0 && getPoint(list2, x, y).getUp() == 1){	//上
									        if (goPath(x, y - 1))
									            return true;
									    }
									    if (!finish){//四周都不通
									    	getPoint(list2, x, y).setOnPath(true);
									    }
									    

									    return finish;
									    
							}
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								for (Mazepoint mazepoint : list2) {
									mazepoint.setVisted(false) ;
								}
								
								goPath(startx,starty);
								for (Mazepoint mazepoint : list2) {
									if(mazepoint.getOnPath()){
										Circle circle = getCenter(mazepoint.getX(),mazepoint.getY());
										pane.getChildren().add(circle);
									}
								}
								
							}
						});
						//单步寻路
						buttonzd.setOnAction(new EventHandler<ActionEvent>() {
						ArrayList<Mazepoint>list5=new ArrayList<>();
							int k=0;
							Circle getCenter(double x, double y){
								double gridSize = (scene.getWidth() - 100) / (h);
								x *= gridSize;
								y *= gridSize;
								
								double radius = gridSize/4;
								double cx = x + gridSize/2;
								double cy = y + gridSize/2;
								Circle circle = new Circle(cx, cy, radius);
								circle.setFill(Color.RED);
								return circle;	
								
							}
							int width = maze2.getWideth();
							int height = maze2.getHeight();
							
							int startx = 0;
							int starty = 0;
							int endx = maze2.getWideth() - 1;
							int endy = maze2.getHeight() - 1;
							Mazepoint getPoint(ArrayList<Mazepoint> list, int x, int y){
								return list4.get(x + y*height);
							}
							
							//开始走
							boolean singlePath(int x, int y)
							{
							    boolean finish = false;
							

							    //已访问过，退出
							    if(getPoint(list4, x, y).isVisted()){
							    	return finish;
							    }
							    list5.add(getPoint(list4,x,y));
							    
							  
							    //如果到达重点则将end置为0表示迷宫已经走结束
							    if (x == endx && y == endy){
							        finish = true;
							    }
							    
							    getPoint(list4, x, y).setVisted(true);
							    
							    //如果迷宫没有走结束则将搜索所在位置的右、下、左、上四个方向是否能够走通
							    if (!finish && x + 1 < width && getPoint(list4, x, y).getRight() == 1){		//右
							        if (singlePath(x + 1, y))
							            return true;
							    }
							    if (!finish && y + 1 < height && getPoint(list4, x, y).getDown() == 1){		//下
							        if (singlePath(x, y + 1))
							            return true;
							    }
							    if (!finish && x - 1 >= 0 && getPoint(list4, x, y).getLeft() == 1){		//左
							        if (singlePath(x - 1, y))
							            return true;
							    }
							    
							    if (!finish && y - 1 >= 0 && getPoint(list4, x, y).getUp() == 1){	//上
							        if (singlePath(x, y - 1))
							            return true;
							    }
							    
							    
							    if (!finish){//四周都不通
							    	getPoint(list4, x, y).setOnPath(false);
							    }

							    return finish;
							}
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub	
								for (int i=0;i<list4.size();i++) {
									list4.get(i).setVisted(false);
								}
								singlePath(startx, starty);
								Circle circle = getCenter(list5.get(k).getX(),list5.get(k).getY());
								pane.getChildren().add(circle);
								k++;
							}
						});
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();
					}
				});

				VBox box3 = new VBox();
				box3.getChildren().addAll(field1, field2);
				box3.setMargin(field1, new Insets(10, 10, 10, 10));
				box3.setMargin(field2, new Insets(10, 10, 10, 10));
				VBox box4 = new VBox();
				box4.setMargin(buttons, new Insets(20, 20, 20, 20));
				box4.getChildren().add(buttons);
				borderPane1.setBottom(box4);
				borderPane1.setTop(box3);
				Pane pane3 = new Pane();
				pane3.getChildren().add(borderPane1);
				Scene scene1 = new Scene(pane3, 200, 150);
				Stage stage1 = new Stage();
				stage1.setScene(scene1);
				stage1.show();

			}
		});

		/*
		 * maze maze1=new maze(); maze.mazeModel maze2=maze1.new mazeModel(w,
		 * h); Pane pane=new Pane(); Scene scene=new Scene(pane,500,500);
		 * ArrayList<Mazepoint>list =maze2.getMaze(); for(int
		 * i=0;i<list.size();i++) { double x=list.get(i).x; double
		 * y=list.get(i).y; if(list.get(i).down==0) { Line line=new Line();
		 * line.setStroke(Color.BLACK);
		 * line.setStartX((scene.getWidth()/(h))*x);
		 * line.setEndX((scene.getWidth()/(h))*(x+1));
		 * line.setStartY((scene.getHeight()/(h))*(y+1));
		 * line.setEndY((scene.getHeight()/(h))*(y+1));
		 * pane.getChildren().add(line); } if(list.get(i).up==0) { Line line=new
		 * Line(); line.setStroke(Color.BLACK);
		 * line.setStartX((scene.getWidth()/(h))*x);
		 * line.setEndX((scene.getWidth()/(h))*(x+1));
		 * line.setStartY((scene.getHeight()/(h))*(y));
		 * line.setEndY((scene.getHeight()/(h))*(y));
		 * pane.getChildren().add(line); } if(list.get(i).left==0) { Line
		 * line=new Line(); line.setStroke(Color.BLACK);
		 * line.setStartX((scene.getWidth()/(w*h))*x);
		 * line.setEndX((scene.getWidth()/(w*h))*(x));
		 * line.setStartY((scene.getHeight()/(w*h))*(y));
		 * line.setEndY((scene.getHeight()/(w*h))*(y+1));
		 * pane.getChildren().add(line); } if(list.get(i).right==0) { Line
		 * line=new Line(); line.setStroke(Color.BLACK);
		 * line.setStartX((scene.getWidth()/(h))*(x+1));
		 * line.setEndX((scene.getWidth()/(h))*(x+1));
		 * line.setStartY((scene.getHeight()/(h))*(y));
		 * line.setEndY((scene.getHeight()/(h))*(y+1));
		 * pane.getChildren().add(line); }
		 * 
		 * }
		 * 
		 * Stage stage=new Stage(); stage.setScene(scene); stage.show(); } });
		 */

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 15, 15, 15));
		hbox.setMargin(button1, new Insets(50, 200, 50, 50));
		hbox.setMargin(button2, new Insets(50, 50, 50, 50));
		hbox.getChildren().addAll(button1, button2);

		Text text = new Text("欢迎");
		text.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1));
		Text text2 = new Text("来到");
		text2.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1));
		Text text3 = new Text("神奇的");
		text3.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1));
		Text text4 = new Text("迷宫世界");
		text4.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1));
		text.setFont(new Font(50));
		text2.setFont(new Font(50));
		text3.setFont(new Font(50));
		text4.setFont(new Font(50));

		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(5, 5, 5, 5));
		hbox2.setMargin(text, new Insets(3, 3, 3, 3));
		hbox2.setMargin(text2, new Insets(3, 3, 3, 3));
		hbox2.setMargin(text3, new Insets(3, 3, 3, 3));
		hbox2.setMargin(text4, new Insets(3, 3, 3, 3));
		hbox2.getChildren().addAll(text, text2, text3, text4);
		BorderPane pane = new BorderPane();
		pane.setBottom(hbox);
		pane.setTop(hbox2);

		Scene scene = new Scene(pane, 600, 200);
		primaryStage.setTitle("迷宫小程序");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

}
 class mazeModel {
	private int wideth;
	private int height;
	private Random rnd = new Random();

	public mazeModel(int wideth, int height) {
		super();
		this.wideth = wideth;
		this.height = height;
	}

	public int getWideth() {
		return wideth;
	}

	public void setWideth(int wideth) {
		this.wideth = wideth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Mazepoint> getMaze() {
		ArrayList<Mazepoint> maze = new ArrayList<>();
		for (int h = 0; h < height; h++)
			for (int w = 0; w < wideth; w++) {
				Mazepoint point = new Mazepoint(w, h);
				maze.add(point);
			}

		return CreatMaze(maze);
	}

	private ArrayList<Mazepoint> CreatMaze(ArrayList<Mazepoint> maze) {
		// TODO Auto-generated method stub
		int top = 0;
		int x = 0;
		int y = 0;
		ArrayList<Mazepoint> team = new ArrayList<Mazepoint>();
		team.add(maze.get(x + y * wideth));
		while (top >= 0) {
			int[] val = new int[] { -1, -1, -1, -1 };
			int times = 0;
			boolean flag = false;
			Mazepoint pt = (Mazepoint) team.get(top);
			x = pt.getX();
			y = pt.getY();
			pt.setVisted(true) ;
			rol1: while (times < 4) {
				int dir = rnd.nextInt(4);
				if (val[dir] == dir)
					continue;
				else
					val[dir] = dir;

				switch (dir) {
				case 0:// 左
					if ((x - 1) >= 0 && maze.get(x - 1 + y * wideth).isVisted()== false) {
						maze.get(x + y * wideth).setLeft();
						maze.get(x - 1 + y * wideth).setRight();
						team.add(maze.get(x - 1 + y * wideth));
						top++;
						flag = true;
						break rol1;
					}
					break;
				case 1:// 右
					if ((x + 1) < wideth && maze.get(x + 1 + y * wideth).isVisted() == false) {

						maze.get(x + y * wideth).setRight();
						maze.get(x + 1 + y * wideth).setLeft();
						team.add(maze.get(x + 1 + y * wideth));
						top++;
						flag = true;
						break rol1;
					}
					break;
				case 2:// 上
					if ((y - 1) >= 0 && maze.get(x + (y - 1) * wideth).isVisted() == false) {
						maze.get(x + y * wideth).setUp();
						maze.get(x + (y - 1) * wideth).setDown();
						team.add(maze.get(x + (y - 1) * wideth));
						top++;
						flag = true;
						break rol1;
					}
					break;
				case 3: // 下
					if ((y + 1) < height && maze.get(x + (y + 1) * wideth).isVisted()== false) {
						maze.get(x + y * wideth).setDown();
						maze.get(x + (y + 1) * wideth).setUp();
						team.add(maze.get(x + (y + 1) * wideth));
						top++;
						flag = true;
						break rol1;
					}
					break;
				}
				times += 1;
			}
			if (!flag) {
				team.remove(top);
				top -= 1;
			}
		}
		return maze;

	}


}

 class Mazepoint {
		private int left = 0;
		private int right = 0;
		private int down = 0;
		private int up = 0;
		private int x = 0;
		private int y = 0;
		private boolean visted = false;
		private boolean on_path = false;

		public Mazepoint(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getLeft() {
			return left;
		}

		public boolean isVisted() {
			return visted;
		}

		public void setVisted(boolean visted) {
			this.visted = visted;
		}

		public void setLeft() {
			this.left = 1;
		}
		public void setLeft(int x) {
			this.left = x;
		}

		public int getRight() {
			return right;
		}

		public void setRight() {
			this.right = 1;
		}
		
		public void setRight(int x) {
			this.right=x;
		}

		public int getUp() {
			return up;
		}

		public void setUp() {
			this.up = 1;
		}
		public void setUp(int x) {
			this.up = x;
		}

		public int getDown() {
			return down;
		}

		public void setDown() {
			this.down = 1;
		}
		public void setDown(int x) {
			this.down = x;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void setOnPath(boolean on) {
			this.on_path = on;
		}
		
		public boolean getOnPath() {
			return on_path;
		}
	}