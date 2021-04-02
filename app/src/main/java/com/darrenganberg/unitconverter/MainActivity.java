package com.darrenganberg.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner unitSelector;

    private ImageButton btnConvertMetres;
    private ImageButton btnConvertKilogram;
    private ImageButton btnConvertCelsius;

    private Group metreConversions;
    private Group kilogramConversions;
    private Group celsiusConversions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitSelector = (Spinner)findViewById(R.id.unitSelector);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.convertibleUnits, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        unitSelector.setAdapter(adapter);

        unitSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s = (String) parent.getItemAtPosition(position);
                switch (s)
                {
                    case "Metre":
                        showMetreConversion();
                        break;
                   case "Kilogram":
                       showKilogramConversion();
                        break;
                    case "Celsius":
                        showCelsiusConversion();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        metreConversions = findViewById(R.id.metreConversions);
        kilogramConversions = findViewById(R.id.kilogramConversions);
        celsiusConversions = findViewById(R.id.celsiusConversions);

        btnConvertMetres = findViewById(R.id.btnConvertMetres);
        btnConvertMetres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = unitSelector.getSelectedItem().toString();
                if (s.equals("Metre"))
                {
                    //Ensure that user has entered a value to convert and display error if they
                    //haven't
                    EditText valueEntered = (EditText)findViewById(R.id.measurementToConvert);
                    String valueAsString = valueEntered.getText().toString();
                    if ((valueAsString == null) || (valueAsString.isEmpty()))
                    {
                        Toast.makeText(MainActivity.this,
                                getString(R.string.missing_value_error), Toast.LENGTH_LONG).show();
                        return;
                    }

                    //Perform conversions
                    double valueToConvert = Double.valueOf(valueAsString);
                    UnitConverter converter = new UnitConverter();
                    double cms = converter.MetersToCentimeters(valueToConvert);
                    double feet = converter.MetersToFeet(valueToConvert);
                    double inches = converter.MetersToInches(valueToConvert);


                    //Display conversions
                    DecimalFormat df = new DecimalFormat("##.00");
                    TextView cvCentimetre = findViewById(R.id.cvCentimetre);
                    cvCentimetre.setText(df.format(cms));
                    TextView cvFoot = findViewById(R.id.cvFoot);
                    cvFoot.setText(df.format(feet));
                    TextView cvInch = findViewById(R.id.cvInch);
                    cvInch.setText(df.format(inches));
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.conversion_error), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnConvertKilogram = findViewById(R.id.btnConvertKilograms);
        btnConvertKilogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = unitSelector.getSelectedItem().toString();
                if (s.equals("Kilogram"))
                {
                    //Ensure that user has entered a value to convert and display error if they
                    //haven't
                    EditText valueEntered = (EditText) findViewById(R.id.measurementToConvert);
                    String valueAsString = valueEntered.getText().toString();
                    if ((valueAsString == null) || (valueAsString.isEmpty()))
                    {
                        Toast.makeText(MainActivity.this,
                                getString(R.string.missing_value_error), Toast.LENGTH_LONG).show();
                        return;
                    }

                    //Perform conversions
                    UnitConverter converter = new UnitConverter();
                    double valueToConvert = Double.valueOf(valueAsString);
                    double grams = converter.KilogramsToGrams(valueToConvert);
                    double ounces = converter.KilogramsToOunces(valueToConvert);
                    double pounds = converter.KilogramsToPounds(valueToConvert);

                    //Display conversion results
                    //Ensure our results only appear as values rounded to 2 decimal places.
                    DecimalFormat df = new DecimalFormat("##.00");

                    TextView cvGram = findViewById(R.id.cvGram);
                    cvGram.setText(df.format((grams)));

                    TextView cvOunce = findViewById(R.id.cvOunce);
                    cvOunce.setText(df.format(ounces));

                    TextView cvPound = findViewById(R.id.cvPound);
                    cvPound.setText(df.format((pounds)));
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.conversion_error), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnConvertCelsius = findViewById(R.id.btnConvertCelsius);
        btnConvertCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = unitSelector.getSelectedItem().toString();
                if (s.equals("Celsius"))
                {
                    //Ensure that user has entered a value to convert and display error if they
                    //haven't
                    EditText valueEntered = (EditText) findViewById(R.id.measurementToConvert);
                    String valueAsString = valueEntered.getText().toString();
                    if ((valueAsString == null) || (valueAsString.isEmpty()))
                    {
                        Toast.makeText(MainActivity.this,
                                getString(R.string.missing_value_error), Toast.LENGTH_LONG).show();
                        return;
                    }

                    //Perform conversions
                    UnitConverter converter = new UnitConverter();
                    double valueToConvert = Double.valueOf(valueAsString);
                    double fahrenheit = converter.CelsiusToFahrenheit(valueToConvert);
                    double kelvin = converter.CelsiusToKelvin(valueToConvert);

                    //Display conversion results
                    //Ensure our results only appear as values rounded to 2 decimal places.
                    DecimalFormat df = new DecimalFormat("##.00");

                    TextView cvFahrenheit = findViewById(R.id.cvFahrenheit);
                    cvFahrenheit.setText(df.format(fahrenheit));

                    TextView cvKelvin = findViewById(R.id.cvKelvin);
                    cvKelvin.setText(df.format((kelvin)));
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.conversion_error), Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    protected void showMetreConversion()
    {
        kilogramConversions.setVisibility(View.GONE);
        celsiusConversions.setVisibility(View.GONE);
        metreConversions.setVisibility(View.VISIBLE);
    }

    protected void showKilogramConversion()
    {
        metreConversions.setVisibility(View.GONE);
        celsiusConversions.setVisibility(View.GONE);
        kilogramConversions.setVisibility(View.VISIBLE);
    }

    protected void showCelsiusConversion()
    {
        metreConversions.setVisibility(View.GONE);
        kilogramConversions.setVisibility(View.GONE);
        celsiusConversions.setVisibility(View.VISIBLE);
    }

    //A UnitConvert can be used to convert a measurement
    private class UnitConverter
    {
        public UnitConverter(){}

        //Convert a measurement in meters to a measurement in Centimeters
        public double MetersToCentimeters(double meters)
        {
            double conversionFactor = 100.0;
            return (meters * conversionFactor);
        }

        //Convert a mesurement in Meters to its equivalent in Feet.
        public double MetersToFeet(double meters)
        {
            double conversionFactor = 3.281;
            return (meters * conversionFactor);
        }

        //Convert a measurement in Meters to its equivalent in Inches.
        public double MetersToInches(double meters)
        {
            double conversionFactor = 39.37;
            return (meters * conversionFactor);
        }

        //Convert a measurement in Kilograms to its equivalent in Grams.
        public double KilogramsToGrams(double kg)
        {
            double conversionFactor = 1000.0;    // grams in a kg
            return (kg * conversionFactor);
        }

        //Convert a measurement in Kilograms to its equivalent in Ounces
        public double KilogramsToOunces(double kg)
        {
            double conversionFactor = 35.274; //Ounces per kg
            return (kg * conversionFactor);
        }

        //Convert a measurement in Kilograms to its equivalent in Pounds.
        public double KilogramsToPounds(double kg)
        {
            double conversionFactor = 2.20462;
            return (kg * conversionFactor);
        }

        //Convert a measurement in celsius to its Fahrenheit equivalent
        public double CelsiusToFahrenheit(double celsius)
        {
            return (celsius * (9.0/5.0) + 32.0);
        }

        //Convert a measurement in celsius to its Kelvin equivalent.
        public double CelsiusToKelvin(double celsius)
        {
            double conversionFactor = 273.15;
            return (celsius + conversionFactor);
        }


    }
}