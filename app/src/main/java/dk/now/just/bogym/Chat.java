package dk.now.just.bogym;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class Chat extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                // Write some data with a timestamp


                //Later, retrieve the data by ordered date


                if (!messageText.equals("")) {
                    Map map = new HashMap();
                    map.put("message", messageText);
                    map.put("date", ServerValue.TIMESTAMP);

                    myRef.push().setValue(map);


                    messageArea.setText("");
                }
            }
        });
        Query queryRef = myRef.orderByChild("date");
        Log.e("Lol","FUCK");

        queryRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

      /*  A DataSnapshot instance contains data from a Firebase Database location.
        Any time you read Database data, you receive the data as a DataSnapshot.*/
       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String map = dataSnapshot.getValue(String.class);
                String message = map;
                //addMessageBox("You:-\n" + message);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadPost:onCancelled", databaseError.toException());

            }
            public void addMessageBox(String message){
                TextView textView = new TextView(Chat.this);
                textView.setText(message);

                layout.addView(textView);
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });*/

        /*myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                //addMessageBox("You:-\n" + message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void addMessageBox(String message){
                TextView textView = new TextView(Chat.this);
                textView.setText(message);

                layout.addView(textView);
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
*/


    }


}
