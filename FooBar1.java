package FooBar1;

public class FooBar1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print(answer("2*4*3+9*3+5*3"));
    }
    
    public static String answer(String str) { 
        //states variables:
        int numOfStar = 0;
        int numOfPlus = 0;

        //temp variables:
        StringBuilder ans = new StringBuilder(); //answer
        int i, j; //iterator
        char c;
        int strLength = str.length();
        
        //looping through the string
        for(i =0; i<strLength; i++){
            c = str.charAt(i);
            //if isDigit(c)
            if((c >= '0' && c <= '9')){
                ans.append(c);
            }
            else if(c=='*'){
                numOfStar++;
            }
            //(c=='+')
            else{
                numOfPlus++;
                if(numOfStar>0){
                    //print all stars:
                    for (j=0; j<numOfStar; j++){
                        ans.append('*');
                    }
                    numOfStar=0;
                }
            }
            if(i == (strLength-1)){
                //print all remaining stars
                for(j=0;j<numOfStar;j++){
                    ans.append('*');
                }
            }
            
        }
        
        //print all the remaining pluses
        for(i=0; i<numOfPlus;i++){
            ans.append('+');
        }
        
        return ans.toString();
    } 
    
    
}
