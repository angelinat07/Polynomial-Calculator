package com.example.polynomialcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    Button clear, delete, carrot, x, seven, eight, nine, multiply, four, five, six, subtract, one, two, three, plus, zero, equals;
    TextView text, equation;

    Button b;
    private String buttonText;
    private String expression = "";

    public class Click implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.zero) {
                numbers(zero);
            }
            if (view.getId() == R.id.one) {
                numbers(one);
            }
            if (view.getId() == R.id.two) {
                numbers(two);
            }
            if (view.getId() == R.id.three) {
                numbers(three);
            }
            if (view.getId() == R.id.four) {
                numbers(four);
            }
            if (view.getId() == R.id.five) {
                numbers(five);
            }
            if (view.getId() == R.id.six) {
                numbers(six);
            }
            if (view.getId() == R.id.seven) {
                numbers(seven);
            }
            if (view.getId() == R.id.eight) {
                numbers(eight);
            }
            if (view.getId() == R.id.nine) {
                numbers(nine);
            }

            if (view.getId() == R.id.x) {
                xVar(x);
            }

            if (view.getId() == R.id.plus) {
                operators(plus);
            }
            if (view.getId() == R.id.subtract) {
                operators(subtract);
            }
            if (view.getId() == R.id.multiply) {
                operators(multiply);
            }
            if (view.getId() == R.id.carrot) {
                operators(carrot);
            }

            if (view.getId() == R.id.clear) {
                clearExpression(clear);
            }

            if (view.getId() == R.id.delete) {
                deleteExpression(delete);
            }

            if (view.getId() == R.id.equals) {
                calculate(equals);
            }

        }

    }

    public void numbers(View view) {
        b = (Button) view;
        buttonText = b.getText().toString();
        expression += buttonText;
        text.setText(expression);
    }

    public void xVar(View view) {
        expression += "x";
        text.setText(expression);
    }

    public void operators(View view) {
        if (view.getId() == plus.getId()) {
            expression += "+";
            text.setText(expression);
        }
        if (view.getId() == subtract.getId()) {
            expression += "-";
            text.setText(expression);
        }
        if (view.getId() == multiply.getId()) {
            expression += "*";
            text.setText(expression);
        }
        if (view.getId() == carrot.getId()) {
            expression += "^";
            text.setText(expression);
        }
    }

    public void clearExpression(View view) {
        expression = "";
        text.setText(expression);
        equation.setText("");
    }

    public void deleteExpression(View view) {
        if (expression.length() > 0) {
            expression = expression.substring(0, expression.length() - 1);
            text.setText(expression);
        }
    }

    /*
    public void answer(View view){
    if (view.getId() == equals.getId()){
    StringTokenizer string = new StringTokenizer(expression, "+-*^", true);
    int tokenCount = string.countTokens();

    ArrayList<String> functions = new ArrayList<String>();
    ArrayList<Double> nums = new ArrayList<Double>();

    for (int i = 0; i < tokenCount; i++){
    String temp = (String)string.nextToken();
    try{
    nums.add(Double.parseDouble(temp));
    }
    catch (NumberFormatException nfe){
    if (i != 0){
    functions.add(temp);
    }
    else if ((i == 0) && (temp.equals("-"))){
    temp = (String)string.nextToken();
    nums.add(-1 * (double) Integer.parseInt(temp));
    i++;
    }
    }
    }
    if (functions.size() >= nums.size()){
    //checks to make sure that equations like 2+++3 or 2*7* print ERROR
    text.setText("ERROR");
    expression = "";
    }

    while (gotM(functions)){
    for (int i = 0; i < functions.size(); i++){
    if (functions.get(i).equals("*")){
    try{
    nums.remove(i + 1);
    }
    catch(Exception e){

    }
    i = 0;
    }
    }
    }
    while (gotAS(functions)) {
    for (int i = 0; i < functions.size(); i++)
    if (functions.get(i).equals("+")) {
    try {
    nums.set(i, nums.get(i) + nums.get(i + 1));
    //text.setText(Double.toString(tempNum.get(t)));
    } catch (Exception e) {
    text.setText("ERROR");
    }
    functions.remove(i);
    try {
    nums.remove(i + 1);
    } catch (Exception e) {
    }
    i = 0;

    } else if (functions.get(i).equals("-")) {
    try {
    nums.set(i, nums.get(i) - nums.get(i + 1));
    //text.setText(Double.toString(tempNum.get(t)));
    } catch (Exception e) {
    text.setText("ERROR");
    }
    functions.remove(i);
    try {
    nums.remove(i + 1);
    } catch (Exception e) {
    }
    i = 0;
    }
    }

    }
    }
    */
    public void calculate(View view) {
        if (view.getId() == equals.getId())
            equation.setText(expression);
        //STARTS CALCULATING PART
        ArrayList<String> arr = new ArrayList<String>();
        StringTokenizer string = new StringTokenizer(expression, "+-*", true);

        while(string.hasMoreTokens()){
            arr.add(string.nextToken());
        }
        errors(arr);
        if (!text.getText().equals("ERROR")) {

//Multiplication

        for(int i = 0; i < arr.size(); i ++){
            if(arr.get(i).equals("*")){
                arr.set(i-1, Multiply(arr.get(i-1), arr.get(i+1)));
                arr.remove(i+1);
                arr.remove(i);
                i--;
            }
        }

 //           mult(arr);

//Addition
            order(arr);
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).equals("+")) {
                    if (getExponent(arr.get(i - 1)) == getExponent(arr.get(i + 1))) {
                        arr.set(i - 1, add(arr.get(i - 1), arr.get(i + 1)));
                        arr.remove(i + 1);
                        arr.remove(i);
                        i--;
                    }
                }
            }
//
//Subtraction
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).equals("-")) {
                    if (getExponent(arr.get(i - 1)) == getExponent(arr.get(i + 1))) {
                        arr.set(i - 1, subtract(arr.get(i - 1), arr.get(i + 1)));
                        arr.remove(i + 1);
                        arr.remove(i);
                        i--;
                    }
                }
            }
            String finalResult = "";
            for (int i = 0; i < arr.size(); i++) {
                finalResult = finalResult + arr.get(i);
            }
            finalResult = doubleOppCheck(finalResult);
            text.setText(finalResult);

        }
    }

    //Methods
    public static String Multiply(String term1, String term2){
        String variable = "";
        String result = "";
        int newCoeff = 0;
        int newExpo = 0;
        newCoeff = getCoeff(term1)*getCoeff(term2);
        if(term1.indexOf("x") > -1 && term2.indexOf("x") > -1)
            newExpo = getExponent(term1)+getExponent(term2);
        else if(term1.indexOf("x") > -1 && term2.indexOf("x") < 0)
            newExpo = getExponent(term1);
        else if(term2.indexOf("x") > -1 && term1.indexOf("x") < 0)
            newExpo = getExponent(term2);
        if(newExpo > 1)
            variable = "x^"+newExpo;
        else if(newExpo == 1)
            variable = "x";
        result = newCoeff+variable;
        return result;
    }
    // methods start
    public static String add(String term1, String term2){
        String result = "";
        String variable = "";
        int res = 0;
        if(term1.indexOf("x") > -1)
            variable = term1.substring(term1.indexOf("x"), term1.length());
        res = getCoeff(term1)+getCoeff(term2);
        result = res+variable;
        return result;
    }
    public static int getExponent(String str){
        int power = 0;
        if(str.indexOf("^") > -1){
            String exponent = str.substring(str.indexOf("^")+1, str.length());
            power = Integer.parseInt(exponent);
            return power;
        }
        else if(str.indexOf("x") > -1)
            return 1;
        else
            return 0;
    }
    public static int getCoeff(String str){
        int coeff = 0;
        if(str.indexOf("x") < 0)
            coeff = Integer.parseInt(str.substring(0, str.length()));
        else if(str.indexOf("x") == 0)
            coeff = 1;
        else if(str.indexOf("x") > 0)
            coeff = Integer.parseInt(str.substring(0, str.indexOf("x")));
        return coeff;
    }
    public static String subtract(String term1, String term2){
        String result = "";
        String variable = "";
        int res = 0;
        if(term1.indexOf("x") > -1)
            variable = term1.substring(term1.indexOf("x"), term1.length());
        res = getCoeff(term1)-getCoeff(term2);
        result = res+variable;
        return result;
    }
    public static String doubleOppCheck(String str){
        String temp = "";
        String temp2 = "";
        if(str.indexOf("+-") > -1){
            str = str.substring(0, str.indexOf("+-"))+doubleOppCheck(str.substring(str.indexOf("+-")+1));
        }
        return str;
    }


    public static ArrayList<String> order(ArrayList<String> arr){
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i).indexOf("+") < 0 && arr.get(i).indexOf("-") < 0){
                for(int j = i+1; j < arr.size(); j++){
                    if(getExponent(arr.get(j)) > getExponent(arr.get(i))){
                        String temp = arr.get(i);
                        if(arr.get(j-1).equals("-")){
                            if(i == 0){
                                arr.add(0, "-");
                                arr.set(j-1, "+");
                            }
                            else{
                                String temp1 = arr.get(i-1);
                                arr.set(i-1, "-");
                                arr.set(j-1, temp1);
                            }
                        }

                        arr.set(i, arr.get(j));
                        arr.set(j, temp);
                    }
                }
            }

        }
        return arr;
    }

//methods end

    //mult
    public void mult(ArrayList<String> arr){
        for (int i = 0; i < arr.size(); i++){
            if (arr.get(i).equals("*")){
                int multiply = 0;
                int answer = 0;
                String FINAL = "";
                String st1 = arr.get(i - 1);
                String st2 = arr.get(i + 1);
                String save1 = "";
                String save2 = "";
                int ex1 = 1;
                int ex2 = 1;

                for (int j = 0; j < st1.length(); j++){
                    if (st1.substring(j, j + 1).equals("x")){
                        if (j == st1.length() - 1){
                            save1 = "1";
                            ex1 = Integer.parseInt(save1);
                        }
                        else{
                            save1 = st1.substring(j + 1);
                            if (save1.equals("^"))
                                save1 = st1.substring(j + 2);
                            ex1 = Integer.parseInt(save1);
                        }
                        st1 = st1.substring(0, j);
                    }
                }

                for (int j = 0; j < st2.length(); j++){
                    if (st2.substring(j, j + 1).equals("x")){
                        if (j == st2.length() - 1){
                            save2 = "1";
                            ex2 = Integer.parseInt(save2);
                        }
                        else{
                            save2 = st2.substring(j + 1);
                            if (save2.equals("^"))
                                save2 = st2.substring(j + 2);
                            ex2 = Integer.parseInt(save2);
                        }
                        st2 = st2.substring(0, j);
                    }
                }
                ex1 = ex1 * ex2;

                int number1 = Integer.parseInt(st1);
                int number2 = Integer.parseInt(st2);
                multiply = number1 * number2;
                FINAL = Integer.toString(multiply);
                if ((!save1.equals("")) || (!save2.equals(""))){
                    if (ex1 == 1)
                        FINAL += "x";
                    else
                        FINAL += "x^" + ex1;
                }
                arr.remove(i + 1);
                arr.remove(i);
                arr.add(i, FINAL);
                arr.remove(i - 1);
            }
        }
    }
    //methods end

    public void errors(ArrayList<String> arr) {
        //checking to see if the input has double operators
        for (int i = 0; i < arr.size(); i++) {
            if ((arr.get(i).equals("+")) || (arr.get(i).equals("-")) || (arr.get(i).equals("*"))) {
                if ((arr.get(i + 1).equals("+")) || (arr.get(i + 1).equals("-")) || (arr.get(i + 1).equals("*")))
                    text.setText("ERROR");
            }
        }
//checks to see if the input starts / ends with an operator
        if ((arr.get(0).equals("+")) || (arr.get(0).equals("*")))
            text.setText("ERROR");
//THIS ONE IS FLAWED BC IF IT STARTS WITH A ^ THEN IT SHOULD ALSO BE WRONG
        if ((arr.get(arr.size()-1).equals("+")) || (arr.get(arr.size()-1).equals("-")) || (arr.get(arr.size()-1).equals("*")))
//        if ((expression.substring(expression.length()-1).equals("+")) || (expression.substring(expression.length()-1).equals("-")) || (expression.substring(expression.length()-1).equals("*")))
            text.setText("ERROR");
        String last = arr.get(arr.size()-1);
        if (last.substring(last.length()-1).equals("^"))
            text.setText("ERROR");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        carrot = findViewById(R.id.carrot);
        x = findViewById(R.id.x);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        multiply = findViewById(R.id.multiply);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        subtract = findViewById(R.id.subtract);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        plus = findViewById(R.id.plus);
        zero = findViewById(R.id.zero);
        equals = findViewById(R.id.equals);

        text = findViewById(R.id.text);
        equation = findViewById(R.id.equation);

        zero.setOnClickListener(new Click());
        one.setOnClickListener(new Click());
        two.setOnClickListener(new Click());
        three.setOnClickListener(new Click());
        four.setOnClickListener(new Click());
        five.setOnClickListener(new Click());
        six.setOnClickListener(new Click());
        seven.setOnClickListener(new Click());
        eight.setOnClickListener(new Click());
        nine.setOnClickListener(new Click());
        x.setOnClickListener(new Click());
        plus.setOnClickListener(new Click());
        subtract.setOnClickListener(new Click());
        multiply.setOnClickListener(new Click());
        carrot.setOnClickListener(new Click());
        clear.setOnClickListener(new Click());
        delete.setOnClickListener(new Click());
        equals.setOnClickListener(new Click());

    }//closes onCreate

}