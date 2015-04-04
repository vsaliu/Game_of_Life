import processing.core.*; 
import processing.data.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Conway extends PApplet {


//Different colors
//Allow people to post their designs
int ilength = 100;
int jlength = 100;
int speed = 50;
int zoom = 0;
Pixel pixel[][] = new Pixel[ilength][jlength];
boolean run = false;
int last_time;

public void setup() {
  size(600, 650);
  background(150);
  frameRate(60);

  for (int i=0; i<ilength; i++) {
    for (int j=0; j<jlength; j++) {
      pixel[i][j] = new Pixel(i*(width/ilength), j*((height-50)/jlength), false);
      pixel[i][j].display();
    }
  }

  last_time = millis();
}


public void draw() {

  if (millis()-last_time>=100-speed) {
    if (run) {
      Animate();
    }
    last_time = millis();
  }
  
  Controls();
  
  for (int i=0; i<ilength; i++) {
    for (int j=0; j<jlength; j++) {
      pixel[i][j].display();
    }
  }

  //mousePress();
}




public void mousePressed() {
  for (int i=0; i<ilength; i++) {
    for (int j=0; j<jlength; j++) {
      if ( (mouseX>=pixel[i][j].x) && (mouseX<=pixel[i][j].x+(width/ilength)) && (mouseY>=pixel[i][j].y) && (mouseY<=pixel[i][j].y+(height/jlength)) ) {
        if (pixel[i][j].state) {
          pixel[i][j].set_state(false);
        }
        else {
          pixel[i][j].set_state(true);
        }
      }
    }
  }

  if (mouseX < 70 && mouseY > height-50) {

    run = false;

    for (int i=0; i<ilength; i++) {
      for (int j=0; j<jlength; j++) { 
        pixel[i][j].set_state(false);
      }
    }
  }

  if (mouseX > 70 && mouseX < 140 && mouseY > height-50) {
    if (run) {
      run = false;
    }
    else {
      run = true;
    }
  }
  
  if(mouseX > 140 && mouseX < 250 && mouseY > height-50){
   speed = mouseX-140; 
  }
  
}






/*

 void mousePress() {
 if (mousePressed) {
 for (int i=0; i<ilength; i++) {
 for (int j=0; j<jlength; j++) {
 if ( (mouseX>=pixel[i][j].x) && (mouseX<=pixel[i][j].x+(width/ilength)) && (mouseY>=pixel[i][j].y) && (mouseY<=pixel[i][j].y+(height/jlength)) ) {
 if (pixel[i][j].state) {
 pixel[i][j].set_state(false);
 }
 else {
 pixel[i][j].set_state(true);
 }
 }
 }
 }
 
 if (mouseX < 70 && mouseY > height-50) {
 
 run = false;
 
 for (int i=0; i<ilength; i++) {
 for (int j=0; j<jlength; j++) { 
 pixel[i][j].set_state(false);
 }
 }
 }
 
 if (mouseX > 70 && mouseX < 140 && mouseY > height-50) {
 if (run) {
 run = false;
 delay(250);
 }
 else {
 run = true;
 delay(250);
 }
 }
 }
 }
 
 */





class Pixel {
  int x, y;
  boolean state;

  Pixel(int xpos, int ypos, boolean s) {
    x = xpos;
    y = ypos;
    state = s;
  }

  public void set_state(boolean s) {
    state = s;
  }

  public void display() {
    stroke(0);
    if (state) {
      fill(250);
    }
    else {
      fill(100);
    }
    rect(x, y, width/ilength, height/jlength);
  }
}

public void Animate() {
  
  boolean pixel_temp[][] = new boolean[ilength][jlength];
  for (int i=0; i<ilength; i++) {
    for (int j=0; j<jlength; j++) {
      pixel_temp[i][j] = false;
    }
  }
  
  for (int i=0; i<ilength; i++) {
    for (int j=0; j<jlength; j++) {

      if (pixel[i][j].state) {
        int index = 0;
        for (int k=-1; k<2; k++) {
          if (k!=0) {
            try {
              if (pixel[i+k][j].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
            try {
              if (pixel[i+k][j+k].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
            try {
              if (pixel[i][j+k].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
            try {
              if (pixel[i-k][j+k].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
          }
        }
        
        if(index==0 || index==1){
         pixel_temp[i][j] = false; 
        }
        
        if (index==2 || index==3) {
         pixel_temp[i][j] = true;
        }
        
        if(index>3){
         pixel_temp[i][j] = false; 
        }
        
      }
      else {
        int index = 0;
        for (int k=-1; k<2; k++) {
          if (k!=0) {
            try {
              if (pixel[i+k][j].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
            try {
              if (pixel[i][j+k].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
            try {
              if (pixel[i+k][j+k].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
            try {
              if (pixel[i-k][j+k].state) {
                index++;
              }
            }
            catch(ArrayIndexOutOfBoundsException e) {
            }
          }
        }

        if (index==3) {
          pixel_temp[i][j] = true;
        }
        else{
         pixel_temp[i][j] = false; 
        }
      }
    }
  }
  
  for (int i=0; i<ilength; i++) {
    for (int j=0; j<jlength; j++) {
      pixel[i][j].set_state(pixel_temp[i][j]);   
    }
  }
  
  
  
  
}

public void Controls() {
  fill(50, 150, 250);
  rect(0, height-50, 70, 50, 20);
  fill(0);
  textSize(15);
  text("Clear", 15, height-20);
  fill(50, 150, 250);
  rect(70, height-50, 70, 50, 20);
  fill(0);
  textSize(15);
  println(run);
  if (run) {
    text("Stop", 90, height-20);
  }
  else {
    text("Run", 90, height-20);
  }
  
  fill(250);
  rect(140,height-50,100,50);
  fill(50,150,250);
  rect(140,height-50,speed,50); 
  fill(0);
  textSize(15);
  text("Speed", 170, height-20);
  
}

  public int sketchWidth() { return 600; }
  public int sketchHeight() { return 650; }
  static public void main(String args[]) {
    PApplet.main(new String[] { "Conway" });
  }
}
