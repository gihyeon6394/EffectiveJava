- [AWS CodeWhisperer link](https://docs.aws.amazon.com/ko_kr/codewhisperer/latest/userguide/what-is-cwspr.html)  
- [how add to Jetbrains](https://docs.aws.amazon.com/ko_kr/codewhisperer/latest/userguide/whisper-setup-indv-devs.html)

<h3>example case</h3>  
: just write a comment then generate code. <sub>holy... sh..</sub>  

```
    public static void main(String[] args) {
        System.out.println("Hello AWS Toolkit");
    }

    // throw NullPointerException when parmeter is null
    public static void checkNotNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        // make o To Integer
        Integer i = (Integer) o;

        System.out.println(i);
    }

    // catch When throw IOException
    public static void catchIOException() {
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            // throw new Runtime Exception
            throw new RuntimeException();
        }
    }

    // make inner class Extend Exception
    public static class MyException extends Exception {
        public MyException() {
            super();
        }
    }

    // make inner class Extend RuntimeException
    public static class MyRuntimeException extends RuntimeException {
        public MyRuntimeException() {
            super();
        }
    }

    @Test
    public void testMyException() {
        try {
            throw new MyException();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
 
 ```

