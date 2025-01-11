package javaCoding;

public class javaCoding {

    public static void main(String[] args)
    {
        int[] numbers={2,3,4,5,6};
        StringBuilder odd= new StringBuilder();
        StringBuilder even= new StringBuilder();
       for(int num:numbers)
       {
           if(num%2==0)
           {
               even.append(num).append(",");
           }
           else {
               odd.append(num).append(",");
           }
       }

        System.out.println(even.append(odd));
    }


}
