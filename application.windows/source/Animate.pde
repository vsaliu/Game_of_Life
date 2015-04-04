void Animate() {
  
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

