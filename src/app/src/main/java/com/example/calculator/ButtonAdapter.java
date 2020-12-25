package com.example.calculator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

class ButtonAdapter extends ArrayAdapter{
   private Context context;
   private TextView tv;
   private String[] buttonValue;
   private Button[] buttons;
   private boolean operatorSelected = false;
   private boolean clearTheScreen = false;
   private double nbr;
   private String operator = "";


    class viewHolder{
        Button btn;
    }

    public ButtonAdapter(Context applicationContext, Button[] buttons, String[] buttonValue, TextView tv) {
        super(applicationContext, 0, buttons);
        this.tv = tv;
        this.context = applicationContext;
        this.buttons = buttons;
        this.buttonValue = buttonValue;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder vHolder;

        if (convertView == null) {
            vHolder = new viewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            vHolder.btn = (Button) convertView.findViewById(R.id.button);
            convertView.setTag(vHolder);
        }
        else { vHolder = (viewHolder) convertView.getTag(); }

        Button btn = vHolder.btn;
        btn.setText(buttonValue[position]);
        switch (buttonValue[position]){
                case "C":
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            operatorSelected = false;
                            clearTheScreen = true;
                            nbr = 0;
                            operator = "";
                            tv.setText("");
                        }
                    });
                    break;

                case "x":
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(operatorSelected){
                            calcul();
                            tv.setText(String.valueOf(nbr));
                        }
                        else{
                            nbr = Double.valueOf(String.valueOf(tv.getText())).doubleValue();
                            operatorSelected = true;
                        }
                            operator = "*";
                            clearTheScreen = true;
                        }
                    });
                    break;

                case "-":
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {if(operatorSelected){
                            calcul();
                            tv.setText(String.valueOf(nbr));
                        }
                        else{
                            nbr = Double.valueOf(String.valueOf(tv.getText())).doubleValue();
                            operatorSelected = true;
                        }
                            operator = "-";
                            clearTheScreen = true;
                        }
                    });
                    break;

                case "+":
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {if(operatorSelected){
                            calcul();
                            tv.setText(String.valueOf(nbr));
                        }
                        else{
                            nbr = Double.valueOf(String.valueOf(tv.getText())).doubleValue();
                            operatorSelected = true;
                        }
                            operator = "+";
                            clearTheScreen = true;
                        }
                    });
                    break;

                case "=":
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            calcul();
                            clearTheScreen = true;
                            operatorSelected = false;
                        }
                    });
                    break;

                case ".":
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String str;
                            if(!tv.getText().equals("0"))
                                    str = tv.getText() + ".";
                            else
                                str = "0.";
                            tv.setText(str);
                        }
                    });
                    break;

                case "/":
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                                public void onClick(View v) {
                                    if(operatorSelected){
                                        calcul();
                                        tv.setText(String.valueOf(nbr));
                                    }
                                    else{
                                        nbr = Double.valueOf(String.valueOf(tv.getText())).doubleValue();
                                        operatorSelected = true;
                                    }
                                    operator = "/";
                                    clearTheScreen = true;
                                }
                        });
                        break;

                    case "%":
                        break;

                     // Those are numbers
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        // Selection of a number
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //On affiche le chiffre additionnel dans le label
                                String str = buttonValue[position];
                                if(clearTheScreen){
                                    clearTheScreen = false;
                                }
                                else{
                                    if(!tv.getText().equals("0"))
                                        str = tv.getText() + str;
                                }
                                tv.setText(str);
                            }
                        });
                        break;

                    default :

                        break;
            }
        // Return the completed view to render on screen
        return convertView;
    }

    //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
    private void calcul(){
        System.out.println("dkhalna l calcul nbr = " + nbr);
        if(operator.equals("+")){
            nbr = nbr + Double.valueOf(String.valueOf(tv.getText())).doubleValue();
            tv.setText(String.valueOf(nbr));
        }
        if(operator.equals("-")){
            nbr = nbr - Double.valueOf(String.valueOf(tv.getText())).doubleValue();
            tv.setText(String.valueOf(nbr));
        }
        if(operator.equals("*")){
            nbr = nbr * Double.valueOf(String.valueOf(tv.getText())).doubleValue();
            tv.setText(String.valueOf(nbr));
        }
        if(operator.equals("/")){
            try{
                nbr = nbr / Double.valueOf(String.valueOf(tv.getText())).doubleValue();
                tv.setText(String.valueOf(nbr));
            }
            catch(ArithmeticException e) {
                tv.setText("0");
            }
        }
    }
}