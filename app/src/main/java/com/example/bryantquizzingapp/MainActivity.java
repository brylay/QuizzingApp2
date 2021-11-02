package com.example.bryantquizzingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;

import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button answer1, answer2, answer3, answer4;

    TextView score, question;
    TextView test12; //String [] mQuestions = new String[10];
    String[] mQuestions = new String[10];

    String[][] mChoices = new String[10][4];

    String[] mCorrectAnswers = new String[10];

    int mFinalScore;
    final int[] QuestionNum = {0};
    final int[] totalGuesses = {0};

    TextView tvht = findViewById(R.id.tvhttps);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                try {
                    InputStream is = getAssets().open("blayquiz1.xml");

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(is);

                    Element element = doc.getDocumentElement();
                    element.normalize();

                    NodeList nList = doc.getElementsByTagName("question");

                    for (int i = 0; i < nList.getLength(); i++) {

                        Node node = nList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element2 = (Element) node;
                            mQuestions[i] = getValue("stem", element2);
                            mChoices[i][0] = getValue("answerA", element2);
                            mChoices[i][1] = getValue("answerB", element2);
                            mChoices[i][2] = getValue("answerC", element2);
                            mChoices[i][3] = getValue("answerD", element2);
                            mCorrectAnswers[i] = getValue("key", element2);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                Spinner spinner = findViewById(R.id.spinner1);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quizzes, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);

                answer1 = (Button) findViewById(R.id.answer1);
                answer2 = (Button) findViewById(R.id.answer2);
                answer3 = (Button) findViewById(R.id.answer3);
                answer4 = (Button) findViewById(R.id.answer4);

                question = (TextView) findViewById(R.id.questionID);


                question.setText(mQuestions[0]);

                answer1.setText(mChoices[QuestionNum[0]][0]);
                answer2.setText(mChoices[QuestionNum[0]][1]);
                answer3.setText(mChoices[QuestionNum[0]][2]);
                answer4.setText(mChoices[QuestionNum[0]][3]);

                answer1.setBackgroundColor(Color.BLUE);
                answer2.setBackgroundColor(Color.BLUE);
                answer3.setBackgroundColor(Color.BLUE);
                answer4.setBackgroundColor(Color.BLUE);

                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mChoices[QuestionNum[0]][0].equals(mCorrectAnswers[QuestionNum[0]])) {
                            answer1.setEnabled(false);
                            totalGuesses[0] = totalGuesses[0] + 1;
                            answer1.setBackgroundColor(Color.RED);
                        } else {
                            QuestionNum[0] = QuestionNum[0] + 1;
                            totalGuesses[0] = totalGuesses[0] + 1;
                            if (QuestionNum[0] == mQuestions.length) {
                                gameOver();
                            } else {
                                updateQuestion(QuestionNum[0]);
                            }

                        }
                    }

                    ;
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mChoices[QuestionNum[0]][1].equals(mCorrectAnswers[QuestionNum[0]])) {
                            answer2.setEnabled(false);
                            totalGuesses[0] = totalGuesses[0] + 1;
                            answer2.setBackgroundColor(Color.RED);
                        } else {
                            QuestionNum[0] = QuestionNum[0] + 1;
                            totalGuesses[0] = totalGuesses[0] + 1;
                            if (QuestionNum[0] == mQuestions.length) {
                                gameOver();
                            } else {
                                updateQuestion(QuestionNum[0]);
                            }

                        }
                    }
                });
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mChoices[QuestionNum[0]][2].equals(mCorrectAnswers[QuestionNum[0]])) {
                            answer3.setEnabled(false);
                            totalGuesses[0] = totalGuesses[0] + 1;
                            answer3.setBackgroundColor(Color.RED);
                        } else {
                            QuestionNum[0] = QuestionNum[0] + 1;
                            totalGuesses[0] = totalGuesses[0] + 1;
                            if (QuestionNum[0] == mQuestions.length) {
                                gameOver();
                            } else {
                                updateQuestion(QuestionNum[0]);
                            }

                        }
                    }
                });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mChoices[QuestionNum[0]][3].equals((mCorrectAnswers[QuestionNum[0]]))) {
                            answer4.setEnabled(false);
                            totalGuesses[0] = totalGuesses[0] + 1;
                            answer4.setBackgroundColor(Color.RED);
                        } else {
                            QuestionNum[0] = QuestionNum[0] + 1;
                            totalGuesses[0] = totalGuesses[0] + 1;
                            if (QuestionNum[0] == mQuestions.length) {
                                gameOver();
                            } else {
                                updateQuestion(QuestionNum[0]);
                            }

                        }

                    }
                });

            }

            private void updateQuestion(int num) {
                question.setText(mQuestions[num]);
                answer1.setText(mChoices[num][0]);
                answer2.setText(mChoices[num][1]);
                answer3.setText(mChoices[num][2]);
                answer4.setText(mChoices[num][3]);
                answer1.setEnabled(true);
                answer2.setEnabled(true);
                answer3.setEnabled(true);
                answer4.setEnabled(true);
                answer1.setBackgroundColor(Color.BLUE);
                answer2.setBackgroundColor(Color.BLUE);
                answer3.setBackgroundColor(Color.BLUE);
                answer4.setBackgroundColor(Color.BLUE);
            }

    public static class Extractor {
        private static CollationElementIterator tvht;

        public static void main(String[] args) throws IOException {
            org.jsoup.nodes.Document doc = (org.jsoup.nodes.Document) Jsoup.connect("https://sites.google.com/asianhope.org/mobileresources/home").get();
            Elements links = doc.select("a[href]");
            Set<String> quizLinks = new HashSet<String>();
            for(org.jsoup.nodes.Element link:links)
            {
                if(link.attr("href").contains("mobileresources/q"))
                {
                    quizLinks.add("https://sites.google.com"+link.attr("href"));
                }
            }
            System.out.println(quizLinks.size()+" quizzes found");
            ArrayList<String> quizzes = new ArrayList<String>();
            for(String url:quizLinks)
            {
                System.out.println("connecting to "+url);
                doc = (org.jsoup.nodes.Document) Jsoup.connect(url).get();
                quizzes.add(extractQuiz(doc.html()));

            } /*
            tvht.setText(quizzes.size()+" quizzes extracted"); */

        }

        private static String extractQuiz(String html) throws IOException {
            boolean strictMode = true;
            String paragraphTagOpen = "<p[^>]+>";
            String paragraphTagClose = "</p[^>]*>";
            String quizTagOpen = "<quiz";
            String quizTagClose ="</quiz>";



            String quiz = html;
            quiz = Parser.unescapeEntities(quiz, strictMode);
            int beginQuizXml = quiz.lastIndexOf(quizTagOpen);
            int endQuizXml = quiz.lastIndexOf(quizTagClose) + quizTagClose.length();

            Validate.isTrue(beginQuizXml>=0&&endQuizXml>=0," quiz not found ");

            quiz = quiz.substring(beginQuizXml, endQuizXml).replaceAll(paragraphTagOpen, "")
                    .replaceAll(paragraphTagClose, "").trim();
            return quiz;
        }
    }

            private void gameOver() {
                mFinalScore = Math.round((float) (QuestionNum[0]) / totalGuesses[0] * 100);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder
                        .setMessage("Game Finished! Your Score is " + mFinalScore + " points")
                        .setCancelable(false)
                        .setPositiveButton("NEW GAME",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                    }
                                })
                        .setNegativeButton("EXIT",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

            private static String getValue(String tag, Element element) {
                NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
                Node node = nodeList.item(0);
                return node.getNodeValue();
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String text = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }
