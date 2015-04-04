//Written by Veton Saliu 2/25/2013
//Different colors
//Allow people to post their designs
int ilength = 100;
int jlength = 100;
int speed = 50;
int zoom = 0;
Pixel pixel[][] = new Pixel[ilength][jlength];
boolean run = false;
int last_time;

void setup() {
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


void draw() {

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




void mousePressed() {
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

  void set_state(boolean s) {
    state = s;
  }

  void display() {
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

