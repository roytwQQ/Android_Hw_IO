package com.example.android_hw3_form;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private RadioGroup radGroupBlood;
	private RadioButton rad_O,rad_A,rad_B,rad_AB;
	
	private TextView showBlood, showDegree, showName;
	private EditText inputName;

	private Spinner spnPrefer;
	String[] Degrees = new String[] {"", "博士", "碩士", "大學", "高中"};
	
	private Button btnSave, btnShow;
	private String blood, degree;
    

	private RadioGroup.OnCheckedChangeListener radGroupListener=
	    	new RadioGroup.OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					int  p = group.indexOfChild((RadioButton) findViewById(checkedId));	
					int count = group.getChildCount(); 
					
					if (checkedId == R.id.radio0){
//						showBlood.setText(rad_O.getText());
						blood = rad_O.getText().toString();
					}
					else if (checkedId == R.id.radio1){
//						showBlood.setText(rad_A.getText());
						blood = rad_A.getText().toString();
					}
					else if (checkedId == R.id.radio2){
//						showBlood.setText(rad_B.getText());
						blood = rad_B.getText().toString();
					}
					else if (checkedId == R.id.radio3){
//						showBlood.setText(rad_AB.getText());
						blood = rad_AB.getText().toString();
					}
				}
	    };
	    
    private Spinner.OnItemSelectedListener spnPreferListener=
        	new Spinner.OnItemSelectedListener(){
    			@Override
    			public void onItemSelected(AdapterView<?> parent, View v,
    					int position, long id) {
    				String sel=parent.getSelectedItem().toString();
//    				showDegree.setText(sel);
    				degree = sel;
    			}
    			@Override
    			public void onNothingSelected(AdapterView<?> parent) {
    				// TODO Auto-generated method stub				
    			}			
        };  
        
//        TextWatcher watcher=new TextWatcher(){
//            public void onTextChanged(    CharSequence text,    int arg1,    int arg2,    int arg3){
//            	showName.setText(text);
//            }
//            public void beforeTextChanged(    CharSequence text,    int arg1,    int arg2,    int arg3){
//            }
//            public void afterTextChanged(    Editable gitDirEditText){
////              updateUIWithValidation();
//            }
//          };
          
      private Button.OnClickListener showListener = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String dir = Environment.getExternalStorageDirectory()+File.separator+"hihihi";
		    File file = new File(dir, "yoman.txt");
			
			StringBuilder text = new StringBuilder();

			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line;

			    while ((line = br.readLine()) != null) {
			        text.append(line);
			        text.append('\n');
			    }
			    br.close();
			}
			catch (IOException e) {
			}

			TextView tv = (TextView)findViewById(R.id.textView4);
			tv.setText(text);
		}
    	  
      };
      private Button.OnClickListener saveListener = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
						 
			    try {
		            
			    	String dir = Environment.getExternalStorageDirectory()+File.separator+"hihihi";
				    //create folder
				    File folder = new File(dir); //folder name
				    folder.mkdirs();

				    //create file
				    File file = new File(dir, "yoman.txt");
				    file.createNewFile();
		            FileOutputStream fOut = new FileOutputStream(file);
		            OutputStreamWriter myOutWriter = 
		                                    new OutputStreamWriter(fOut);
		            
		            String name = inputName.getText().toString();
		             
		            myOutWriter.append(name+"的血型是"+blood+"，學歷是"+degree);
		            myOutWriter.close();
		            fOut.close();
		            
		        } catch (Exception e) {
		            
		        }
			
		}

	
    	  
      };

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputName = (EditText) findViewById(R.id.editText1);
//        showBlood=(TextView) findViewById(R.id.textView4);
//        showDegree=(TextView) findViewById(R.id.textView5);
//        showName=(TextView) findViewById(R.id.textView6);
        radGroupBlood=(RadioGroup) findViewById(R.id.radioGroup1); 
        rad_O = (RadioButton) findViewById(R.id.radio0);
        rad_A = (RadioButton) findViewById(R.id.radio1);
        rad_B = (RadioButton) findViewById(R.id.radio2);
        rad_AB = (RadioButton) findViewById(R.id.radio3);
        radGroupBlood.setOnCheckedChangeListener(radGroupListener); 

//        inputName.addTextChangedListener(watcher);
        
        btnSave = (Button)findViewById(R.id.button1);
        btnShow = (Button)findViewById(R.id.button2);
        
        spnPrefer = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapterDegree = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Degrees);
        adapterDegree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrefer.setAdapter(adapterDegree);
        spnPrefer.setOnItemSelectedListener(spnPreferListener); 
        
        btnSave.setOnClickListener(saveListener);
        btnShow.setOnClickListener(showListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
