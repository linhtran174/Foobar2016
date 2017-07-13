    public static void die(int x, int shift) throws Exception{
        int result = x - shift;
        
        if (result ==  0) throw new ArithmeticException();
        if (result ==  1) throw new ArrayIndexOutOfBoundsException();
        if (result ==  2) throw new ArrayStoreException();
        if (result ==  3) throw new ClassCastException();
        if (result ==  4) throw new ClassNotFoundException();
        if (result ==  5) throw new CloneNotSupportedException();
        if (result ==  6) throw new ConcurrentModificationException();
        if (result ==  7) throw new EmptyStackException();
        if (result ==  8) throw new IllegalAccessException("Method answer is not accessible..");
        if (result ==  9) throw new IllegalArgumentException();
        if (result == 10) throw new IllegalMonitorStateException();
        if (result == 11) throw new IllegalStateException();
        if (result == 12) throw new IllegalThreadStateException();
        if (result == 13) throw new IllformedLocaleException();
        if (result == 14) throw new IndexOutOfBoundsException();
        if (result == 15) throw new InstantiationException();
        if (result == 16) throw new InterruptedException();
        if (result == 17) throw new MissingResourceException("", "", "");
        if (result == 18) throw new NegativeArraySizeException();
        if (result == 19) throw new NoSuchElementException();
        if (result == 20) throw new NullPointerException();
        if (result == 21) throw new NumberFormatException();
        if (result == 22) throw new SecurityException();
        if (result == 23) throw new StringIndexOutOfBoundsException();
        if (result == 24) throw new TypeNotPresentException("", new Throwable());
        if (result == 25) throw new UnsupportedOperationException();
        return;
    }
