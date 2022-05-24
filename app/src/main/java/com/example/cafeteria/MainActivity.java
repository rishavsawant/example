package com.example.cafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity=0;
    CheckBox cb;
    TextView email;
    Button b4;
    int qt=0;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=findViewById(R.id.incr);
        cb=findViewById(R.id.check);
        t1=(TextView) findViewById(R.id.dlvr);
        Button b2=findViewById(R.id.decr);
        email=(TextView) findViewById(R.id.text_email);
        Button b3=findViewById(R.id.order);
        b4=findViewById(R.id.next);
        Button b5=findViewById(R.id.prev);
        TextView textView=(TextView) findViewById(R.id.text);
        //TextView textView1=findViewById(R.id.price_textview);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity < 20) {
                    quantity++;
                    textView.setText(" " + quantity);
                    // textView1.setText("$"+getPrice(quantity));

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(quantity>0){
                        quantity--;
                    }
                    textView.setText(" " + quantity);
                   // textView1.setText("$" + getPrice(quantity));
                }

        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb.isChecked()){
                    t1.setText("Delivery charge:Rs 100");

                }
                else{
                    t1.setText("Delivery charge:Rs 0");
                }



            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Coffee cf=new Coffee();
                pastry pt=new pastry();
                cake ck=new cake();
                Fragment fmm=new Fragment();
                FragmentManager fm=getSupportFragmentManager();
                switch (qt){
                    case 0:
                       fmm=ck;
                        break;
                    case  1:
                        fmm=cf;
                        break;
                    case 2:
                        fmm=pt;

                }
                if(qt<2) {
                   qt++;

                }

                fm.beginTransaction().replace(R.id.frg_cont,fmm).commit();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                Coffee cf=new Coffee();
                cake ck=new cake();
                pastry pt=new pastry();
                Fragment fmm=new Fragment();
                switch (qt){
                    case 0:
                        fmm=ck;
                        break;
                    case  1:
                        fmm=cf;
                        break;
                    case 2:
                        fmm=pt;
                        break;


                }

                fm.beginTransaction().replace(R.id.frg_cont,fmm).commit();
                if(qt>0) qt--;

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order from Cafeteria");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }





        });

}
int getPrice(int t){
        int price=t*5;
        return price;
}
}