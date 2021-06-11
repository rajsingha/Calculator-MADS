package com.calculator.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.calculator.app.databinding.ActivityMainBinding;
import com.calculator.app.model.HistoryData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    public ArrayList<HistoryData> historyDataList;
    TextView inputTxt, outputTxt;
    String strFinal, strResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();


    }

    private void initView() {

        historyDataList = new ArrayList<>();

        inputTxt = binding.inputTxt;
        outputTxt = binding.outPuttxt;

        binding.btn0.setOnClickListener(v -> {

            inputTxt.setText(inputTxt.getText() + "0");
        });

        binding.btn1.setOnClickListener(v -> {

            inputTxt.setText(inputTxt.getText() + "1");
        });

        binding.btn2.setOnClickListener(v -> {
            inputTxt.setText(inputTxt.getText() + "2");
        });

        binding.btn3.setOnClickListener(v -> {

            inputTxt.setText(inputTxt.getText() + "3");
        });

        binding.btn4.setOnClickListener(v -> {
            inputTxt.setText(inputTxt.getText() + "4");
        });

        binding.btn5.setOnClickListener(v -> {

            inputTxt.setText(inputTxt.getText() + "5");
        });

        binding.btn6.setOnClickListener(v -> {
            inputTxt.setText(inputTxt.getText()+ "6");
        });

        binding.btn7.setOnClickListener(v -> {

            inputTxt.setText(inputTxt.getText() + "7");
        });

        binding.btn8.setOnClickListener(v -> {
            inputTxt.setText(inputTxt.getText() + "8");
        });

        binding.btn9.setOnClickListener(v -> {
            inputTxt.setText(inputTxt.getText() + "9");
        });

        binding.btnAc.setOnClickListener(v -> {
            inputTxt.setText("");
            binding.outPuttxt.setText("");
        });

        binding.btnPlus.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(inputTxt.getText())) {
                if (checkOperator()) {
                    inputTxt.setText(inputTxt.getText() + "+");
                }
            }
        });

        binding.btnMinus.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(inputTxt.getText())) {
                if (checkOperator()) {
                    inputTxt.setText(inputTxt.getText() + "-");
                }
            }
        });

        binding.btnDivide.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(inputTxt.getText())) {
                if (checkOperator()) {
                    inputTxt.setText(inputTxt.getText() + "/");
                }
            }
        });

        binding.btnMultiplication.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(inputTxt.getText())) {
                if (checkOperator()) {
                    inputTxt.setText(inputTxt.getText()+ "*");
                }
            }
        });

        binding.btnEqual.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(inputTxt.getText())) {
                if (!isLastNumber()) {
                    madsCalculations();
                } else {
                    Toast.makeText(getApplicationContext(), "invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnHistory.setOnClickListener(v -> {
            Intent i = new Intent(this,HistoryActivity.class);
            i.putExtra("history_data",new Gson().toJson(historyDataList));
            startActivity(i);
        });
    }

    private void madsCalculations() {
        strFinal = inputTxt.getText().toString().trim();

        String[] operator = new String[]{"*", "+", "/", "-"};
        List<String> operatorlist = Arrays.asList(operator);
        List<String> myList = new ArrayList<String>();

        int operatorCount = 0;
        int nextCounter = 0;
        for (int i = 0; i < strFinal.length(); i++) {
            String strOperator = "" + strFinal.charAt(i);

            if (operatorlist.contains(strOperator)) {
                String leftString = strFinal.substring(nextCounter, i);

                myList.add(leftString);
                myList.add(strOperator);
                operatorCount++;
                Log.d("found_operator", leftString + "," + strOperator);
                nextCounter = i + 1;

            }
        }

        String temp = "";
        for (int i = 0; i < myList.size(); i++) {
            temp = temp + myList.get(i);
        }


        if (temp.length() == strFinal.length())
        {
            if (operatorlist.contains(myList.get(myList.size() - 1))) {
                myList.remove(myList.size() - 1);
                operatorCount--;
            }
        } else {
            String leftString = strFinal.substring(nextCounter);
            myList.add(leftString);
        }

        processArray(myList, operatorlist, operatorCount);

    }


    void processArray(List<String> myList, List<String> operatorlist, int processCount) {
        for (int k = 0; k < operatorlist.size(); k++) {
            String forOperator = operatorlist.get(k);

            for (int j = 0; j < processCount; j++) {
                for (int i = 0; i < myList.size(); i++) {
                    if (operatorlist.contains(myList.get(i)) && forOperator.equals(myList.get(i))) {
                        if (i != 0 && i != myList.size() - 1) {
                            double d = performOperationWithOperator(myList.get(i - 1), myList.get(i), myList.get(i + 1));
                            String s = String.valueOf(d);
                            myList.set(i + 1, s);
                            myList.remove(i);
                            myList.remove(i - 1);
                        }
                        break;
                    }
                }
            }

        }

        Log.d("found_operator", "," + myList.get(0));

        double d = Double.parseDouble(myList.get(0));
        if (d == (long) d) {
            strResult = String.format("%d", (long) d);
        } else {
            strResult = String.format("%s", d);
        }
        outputTxt.setText(strResult);

        if (historyDataList.size() < 10) {
            HistoryData historyData = new HistoryData();
            historyData.setOperation(strFinal);
            historyData.setResults(strResult);
            historyDataList.add(historyData);
        }
        Log.d("Results", historyDataList.toString());
    }

    double performOperationWithOperator(String opLeft, String op, String opRight) {
        try {


            double l = Double.parseDouble(opLeft);
            double r = Double.parseDouble(opRight);

            switch (op) {
                case "*":
                    return (l * r);
                case "+":
                    return (l + r);
                case "/":
                    return (l / r);
                case "-":
                    return (l - r);
                default:
                    return 0;
            }


        } catch (Exception e) {

        }
        return 0;
    }

    private boolean isLastNumber() {
        String last = inputTxt.getText().toString();
        last = last.substring(last.length() - 1);
        return !last.equalsIgnoreCase("0") && !last.equalsIgnoreCase("1") && !last.equalsIgnoreCase("2") && !last.equalsIgnoreCase("3")
                && !last.equalsIgnoreCase("4") && !last.equalsIgnoreCase("5") && !last.equalsIgnoreCase("6") && !last.equalsIgnoreCase("7")
                && !last.equalsIgnoreCase("8") && !last.equalsIgnoreCase("9");
    }

    private boolean checkOperator() {
        String last = inputTxt.getText().toString();
        last = last.substring(last.length() - 1);
        return !last.equalsIgnoreCase("+") && !last.equalsIgnoreCase("-") && !last.equalsIgnoreCase("*") && !last.equalsIgnoreCase("/");
    }

}