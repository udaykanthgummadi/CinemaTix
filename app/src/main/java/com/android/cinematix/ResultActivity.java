package com.android.cinematix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView tvClass, tvMovie, tvRate, tvAdditional, tvJumlah;
    private TextView tvSubTotalTicket, tvSubTotalAddtional, tvTotal;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvClass = (TextView) findViewById(R.id.tv_class);
        tvMovie = (TextView) findViewById(R.id.tv_movie);
        tvRate = (TextView) findViewById(R.id.tv_rate);
        tvAdditional = (TextView) findViewById(R.id.tv_additional);
        tvJumlah = (TextView) findViewById(R.id.tv_jumlah);

        tvSubTotalTicket = (TextView) findViewById(R.id.tv_ticket_subtotal);
        tvSubTotalAddtional = (TextView) findViewById(R.id.tv_additional_subtotal);
        tvTotal = (TextView) findViewById(R.id.tv_total);

        btnShare = (Button) findViewById(R.id.btn_share);

        // Declare Intent here
        Intent intent = getIntent();

        final String classCinematix = intent.getStringExtra("classCinematix");
        tvClass.setText(classCinematix);

        // TODO (16) Buat variable untuk menampung INTENT dari filmCinematix dan masukkan variable tsb
        // ke dalam tvMovie sesuai dengan format classCinematix dan tvClass di atas

        // TODO (17) Masukkan method getPriceBasedOnClass() dengan parameter classCinematix ke dalam variabel rate
        int rate = ;
        tvRate.setText(convertMoney(rate));


        final String additional = intent.getStringExtra("additionalCinematix");
        int priceAdditional = getPriceBasedOnAdditional(additional);
        // TODO (18) panggil method convertMoney() dengan parameter priceAdditional dan masukkan
        // setelah tanda " : "
        tvAdditional.setText(additional+ " : " );

        final String jumlah = intent.getStringExtra("jumlahCinematix");
        tvJumlah.setText(jumlah);

        // Sub total
        int priceClass = getPriceBasedOnClass(classCinematix);

        // TODO (19.A) ubah tipe data variable jumlah menjadi Integer
        // dengan Integer.valueOf()
        int subTotalMovie = priceClass * jumlah;
        tvSubTotalTicket.setText(convertMoney(subTotalMovie));

        // TODO (19.B) ubah tipe data variable jumlah menjadi Integer
        // dengan Integer.valueOf()
        int subTotalAddtional = priceAdditional * jumlah;
        tvSubTotalAddtional.setText(convertMoney(subTotalAddtional));

        // Total
        final int total = subTotalMovie + subTotalAddtional;
        tvTotal.setText(convertMoney(total));

        btnShare = (Button) findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendText = "Memesan " + jumlah + " tiket " +
                        movieCinematix + ". Kelas " + classCinematix +
                        ", include popcorn dan soda : " + additional + ".\n" +
                        "Total Harga : " + convertMoney(total);

                // TODO (20.A) Deklarasikan Intent object dengan nama sendIntent
                Intent sendIntent = new Intent();

                // TODO (20.B) berikan aksi Intent.ACTION_SEND thdp sendIntent
                sendIntent.setAction(Intent.ACTION_SEND);

                sendIntent.putExtra(Intent.EXTRA_TEXT, sendText);

                // TODO (20.C) Set type sendIntent dengan "text/plain"


                startActivity(Intent.createChooser(sendIntent, "Bagikan"));
            }
        });
    }

    public int getPriceBasedOnClass(String classCinema) {
        int price = 0;
        switch (classCinema) {
            case "Ekonomi":
                price = 20000;
                break;
            case "Regular":
                price = 30000;
                break;
            case "Eksekutif":
                price = 50000;
                break;
            default:
                price = 0;
                break;
        }
        return price;
    }

    public int getPriceBasedOnAdditional(String additional) {
        int price = 0;
        switch (additional) {
            case "Ya":
                price = 20000;
                break;
            case "Tidak":
            default:
                price = 0;
                break;
        }
        return price;
    }

    public String convertMoney(int money) {
        return NumberFormat.getNumberInstance(Locale.GERMANY).format(money);
    }


}
