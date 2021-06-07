package com.basic.spanishlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.futuremind.recyclerviewfastscroll.FastScroller;


public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickInterface{

    int a=001;
    FastScroller fastscroller;
    RecyclerView rv_contents;
    String[] str_contents={" 1 – People",
                            " 2 – Family Members",
                            " 3 – Getting to Know Others",
                            " 4 – At School",
                            " 5 – Countries and Languages",
                            " 6 – Reading and Writing",
                            " 7 – Numbers",
                            " 8 – The Time",
                            " 9 – Days of the Week",
                            "10 – Yesterday, Today, Tomorrow",
                            "11 – Months",
                            "12 – Beverages",
                            "13 – Activities",
                            "14 – Colors",
                            "15 – Fruits and Food",
                            "16 – Seasons and Weather",
                            "17 – Around the House",
                            "18 – House Cleaning",
                            "19 – In the Kitchen",
                            "20 – Small Talk 1",
                            "21 – Small Talk 2",
                            "22 – Small Talk 3",
                            "23 – Learning Foreign Languages",
                            "24 – Appointment",
                            "25 – In the City",
                            "26 – In Nature",
                            "27 – In the Hotel – Arrival",
                            "28 – In the Hotel – Complaints",
                            "29 – At the Restaurant 1",
                           "30 – At the Restaurant 2",
                           "31 – At the Restaurant 3",
                            "32 – At the Restaurant 4",
                            "33 – At the Station",
                            "34 – On the Train",
                            "35 – At the Airport",
                            "36 – Public Transportation",
                            "37 – En route",
                            "38 – In the Taxi",
                            "39 – Car Breakdown",
                            "40 – Asking for Directions",
                            "41 – Where is … ?",
                            "42 – City Tour",
                            "43 – At the Zoo",
                            "44 – Going Out in the Evening",
                            "45 – At the Cinema",
                            "46 – In the Discotheque",
                            "47 – Preparing a Trip",
                            "48 – Vacation Activities",
                            "49 – Sports",
                            "50 – In the Swimming Pool"	,"51 – Running Errands",
                            "52 – In the Department Store",
                            "53 – Shops",
                            "54 – Shopping",
                            "55 – Working",
                            "56 – Feelings",
                            "57 – At the Doctor",
                            "58 – Parts of the Body",
                            "59 – At the Post Office",
                            "60 – At the Bank",
                            "61 – Ordinal Numbers",
                            "62 – Asking Questions 1",
                            "63 – Asking Questions 2",
                            "64 – Negation 1",
                           "65 – Negation 2",
                            "66 – Possessive Pronouns 1",
                            "67 – Possessive Pronouns 2",
                             "68 – Big, Small",
                            "69 – To Need, Want To",
                            "70 – To Like Something",
                            "71 – To Want Something",
                            "72 – To Have To, Must",
                            "73 – To Be Allowed To, May",
                            "74 – Asking for Something",
                            "75 – Giving Reasons 1",
                            "76 – Giving Reasons 2",
                            "77 – Giving Reasons 3",
                           "78 – Adjectives 1",
                            "79 – Adjectives 2",
                            "80 – Adjectives 3",
                            "81 – Past Tense 1",
                            "82 – Past Tense 2",
                            "83 – Past Tense 3",
                            "84 – Past Tense 4",
                            "85 – Questions – Past Tense 1",
                            "86 – Questions – Past Tense 2",
                           "87 – Past Tense of Modal Verbs 1",
                            "88 – Past Tense of Modal Verbs 2",
                            "89 – Imperative 1",
                            "90 – Imperative 2",
                            "91 – Subordinate Clauses – That 1",
                            "92 – Subordinate Clauses – That 2",
                            "93 – Subordinate Clauses – If",
                            "94 – Conjunctions 1",
                           "95 – Conjunctions 2",
                           "96 – Conjunctions 3",
                            "97 – Conjunctions 4",
                            "98 – Double Connetors",
                            "99 – Genitive",
                            "100 – Adverbs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_contents=findViewById(R.id.rv_contents);
        rv_contents.setLayoutManager(new LinearLayoutManager(this));
        fastscroller= findViewById(R.id.fs_rv);
        rv_contents.setAdapter(new MyAdapter(str_contents,this));


       fastscroller.setRecyclerView(rv_contents);
    }

    @Override
    public void OnClick(int position) {

        Intent intent =new Intent(this, SpanishActivity.class);
        intent.putExtra("heading", str_contents[position]);
        startActivity(intent);
        Toast.makeText(this, str_contents[position], Toast.LENGTH_SHORT).show();
    }
}
