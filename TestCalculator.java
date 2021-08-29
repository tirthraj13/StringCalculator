import Calculator.*;
import org.junit.*;

public class TestCalculator {

    private StringCalculator cal;

    @Before
    public void SetObject(){
        cal = new StringCalculator();                                       // Creating Object Of StringCalculator Class
    }

    @Test
    public void ReturnZeroOnEmptyString(){                                  // Task - 1 (Null String)
        Assert.assertEquals(0,cal.Add(""));
    }

    @Test
    public void ReturnNumberOnSingleNumber(){                               // Task - 1 (Single Length String)
        Assert.assertEquals(1,cal.Add("1"));
    }

    @Test
    public void ReturnAdditionOfTwoNumberSeperatedByComma(){                // Task - 1 (Two Values Seperated By Comma)
        Assert.assertEquals(3,cal.Add("1,2"));
    }

    @Test
    public void ReturnAdditionOfUnknownNumberOfData(){                      // Task - 2 (Unknown Amount Of Data)
        Assert.assertEquals(6,cal.Add("1,2,3"));
    }

    @Test
    public void AcceptNewlineAsValidDelimeter(){                            // Task - 3 (\n Should Be Valid Delimeter)
        Assert.assertEquals(6,cal.Add("1\n2,3"));
    }

    @Test
    public void AcceptCustomDelimeterSyntex(){                              // Task - 4 (Support Different Delimeter)
        Assert.assertEquals(3,cal.Add("//;\n1;2"));
    }

    @Test
    public void AcceptCustomDelimeterWhichIsRegexSpecialChar(){             // Task - 4 (Support RegEx Special Char As Valid Delimeter)
        Assert.assertEquals(3,cal.Add("//.\n1.2"));
    }

    @Test
    public void NegativeNumberNotAllowed(){                                 // Task - 5 (Negative Num is Not Allowed)
        try {
            cal.Add("-1,2,3");
            Assert.fail();
        }catch(Exception e){
            System.out.print("Negative Numbers Are Not Allowed");
        }
    }

    @Test
    public void NegativeNumbersShouldContainInExeptionMessage(){            // Task - 5 (Negative Num Not Allowed With Exception Msg)
        try {
            cal.Add("-1,2,3");
            Assert.fail();
        }catch(Exception e){
            Assert.assertEquals("Negative Numbers Are Not Allowed Found : -1" , e.getMessage());
        }
    }

    @Test
    public void MultipleNegativeNumbersShowInExeptionMessage(){             // Task - 6 (Multiple Negative Nums Should Print In Exeption Msg)
        try {
            cal.Add("-1,-2,3");
            Assert.fail();
        }catch(Exception e){
            Assert.assertEquals("Negative Numbers Are Not Allowed Found : -1,-2" , e.getMessage());
        }
    }

    @Test
    public void CountOfHowManyTimesAddMethodInvoked(){                      // Task - 7 (Count Number Of Times Add Method Invoked)
        Assert.assertEquals(6,cal.GetCalledCount());                // This Test Is Executed At 7th.At This Time 6 Test Were Executed.
    }                                                                       // That's Why We Expect Answer 6.
                                                                            // In TDD Tests Are Not Executed In Sequence.Execution Order Is Random Or Lexographical.

    @Test
    public void ShouldIgnoreIfNumberIsGreaterThan1000(){                    // Task - 9 (Ignore The Number If It Is Greater Than 1000)
        Assert.assertEquals(2,cal.Add("2,1001"));
    }

    @Test
    public void AcceptDelimeterOfAnyLength(){                               // Task - 10 (Accept Delimeter Of Any Length)
        Assert.assertEquals(6,cal.Add("//[***]\n1***2***3"));
    }

    @Test
    public void AcceptMultipleDelimeterOfLengthOne(){                       // Task - 11 (Accept Multiple Delimeter Of Single Length)
        Assert.assertEquals(6,cal.Add("//[*][%]\n1*2%3"));
    }

    @Test
    public void AcceptMultipleDelimeterOfAnyLength(){                       // Task - 12 (Accept Multiple Delimeter Any Length)
        Assert.assertEquals(6,cal.Add("//[**][%%]\n1**2%%3"));
    }

}
