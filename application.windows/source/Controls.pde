void Controls() {
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

