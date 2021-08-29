package Calculator;
import java.util.*;
import java.util.regex.*;

public class StringCalculator {

    public static int count = 0;

    public int GetCalledCount() {
        return count;
    }

    public int Add(String s) {

        count = count + 1;
        if(s.length()==0){
            return 0;
        }
        else if(s.contains(",") || s.contains("\n")){

            String[] numbers = SplitString(s);

            List<Integer> arrlist = new ArrayList<>();
            List<String> negativeNumbers = new ArrayList<>();

            AddElementsFromArrayToList(arrlist,numbers,negativeNumbers);               //Add Elements From Array To List For Finding The Sum In Easy Way
            CheckForNegativeNumber(negativeNumbers);                                   //This Function Call Is For Throw An Exception If Negative Numbers Found

            return arrlist.stream()                                                    //Return Sum Of The Elements Less Than 1000
                    .filter(i -> i <= 1000 )
                    .mapToInt(i -> i)
                    .sum();

        }
        else{
            return typecast(s);                                                        //Return Integer Converted From String If Single Digit Is Found From String
        }
    }

    private static void CheckForNegativeNumber(List<String> negativeNumbers) {
        if(negativeNumbers.size()>0){
            throw new RuntimeException("Negative Numbers Are Not Allowed Found : "+ String.join(",",negativeNumbers));
        }
    }

    private static  String[] SplitString(String s) {
        if(CustomDelimeterSyntex(s)){                                                 //Check If String Is Start With "//"
            return SplitStringByCustomDelimeter(s);
        }
        else{
            String[] splitedArr = s.split(",|\n");
            return splitedArr;
        }

    }

    private static boolean CustomDelimeterSyntex(String s) {
        return s.startsWith("//");
    }

    private static String[] SplitStringByCustomDelimeter(String s) {

        Pattern pattern1 = Pattern.compile("//(.)\n(.*)");                             //RegEx For Single Length Custom Delimeter
        Pattern pattern2 = Pattern.compile("//\\[(.*?)\\]\\[(.*?)\\]\n(.*)");          //RegEx For Multiple Delimeter Of Any Length
        Pattern pattern3 = Pattern.compile("//\\[(.*?)\\]\n(.*)");                     //RegEx For Single Delimeter Of Any Length

        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        Matcher matcher3 = pattern3.matcher(s);

        boolean match1 = matcher1.matches();                                           // -----|
        boolean match2 = matcher2.matches();                                           // -----|-----Check For Which RegEx Is Matched Among Three
        boolean match3 = matcher3.matches();                                           // -----|

        if(match1) {
            String customDelimeter = matcher1.group(1);
            String numbers = matcher1.group(2);
            return numbers.split(pattern1.quote(customDelimeter));
        }
        else if(match2) {
            String customDelimeter1 = matcher2.group(1);
            String customDelimeter2 = matcher2.group(2);
            String customDelimeter = "[" + "\\" + customDelimeter1 + "|" + "\\" + customDelimeter2 + "]";
            String numbers = matcher2.group(3);
            String[] nums = numbers.split(customDelimeter);
            return RemoveNullString(nums);                                             //call RemoveNullString To Remove Null Strings From Num
        }
        else if(match3){
            String customDelimeter = matcher3.group(1);
            String numbers = matcher3.group(2);
            return numbers.split(pattern3.quote(customDelimeter));
        }


        return new String[]{""};
    }

    private static String[] RemoveNullString(String[] arr) {

        List<String> list=new ArrayList<String>();

        for(int i = 0; i < arr.length; i++){
            if(!arr[i].equals("")){
                list.add(arr[i]);                                                      //Add Elements In list If It Is Not Null
            }
        }

        int listLength = list.size();
        String[] numbers = new String[listLength];

        for(int i = 0; i < listLength;i++){
            numbers[i] = list.get(i);                                                  //Copy Elements From list To numbers Array.
        }
        return numbers;
    }

    private static void AddElementsFromArrayToList(List<Integer> arrList,String[] numbers,List<String> negativeNum) {

        for(int i = 0;i < numbers.length;i++){
            int temp = typecast(numbers[i]);

            if(temp<0){
                negativeNum.add(String.valueOf(temp));                                 //Add Elements In negativeNum List If Number Is Negative
            }
            arrList.add(temp);
        }
    }

    private static int typecast(String s) throws NumberFormatException {
        return Integer.parseInt(s);                                                    //Typecast From String To Integer
    }
}
