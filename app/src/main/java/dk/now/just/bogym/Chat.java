package dk.now.just.bogym;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
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
import com.google.firebase.database.DatabaseException;
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
    DatabaseReference myRef = database.getReference("messages/" + UserDetails.username + "_" + UserDetails.chatWith);
    DatabaseReference newRef = database.getReference("messages/" + UserDetails.chatWith + "_" + UserDetails.username );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    //map.put("date", ServerValue.TIMESTAMP);
                    myRef.push().setValue(map);
                    newRef.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });
       /* Query queryRef = myRef.orderByChild("date");
        Log.e("Lol","Before Method");*/
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                try {
                    Map<String, String> map = (Map<String, String>) dataSnapshot.child("").getValue();
                    String message = map.get("message");
                    String userName = map.get("user");
                    if(userName.equals(UserDetails.username)){
                    addMessageBox("You:-\n" + message,1);
                    }
                    else {
                        addMessageBox(UserDetails.chatWith + ":-\n" + message,2);
                    }
                }
                catch (DatabaseException e){
                   // Map map = dataSnapshot.child("message").getValue(Map.class);
                    Log.d("How", "Hey I am an error" + dataSnapshot.child("message").getValue() );
                    dataSnapshot.getKey();
                    dataSnapshot.getValue();
                    addMessageBox("Something went wrong:" + dataSnapshot.child(dataSnapshot.getKey()+"/message").getValue(String.class), 1);
                }
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

            public void addMessageBox(String message, int type){
                TextView textView = new TextView(Chat.this);
                textView.setText(message);
                LinearLayout.LayoutParams lp2 =
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp2.weight = 1.0f;
                if(type == 1){
                    lp2.gravity = Gravity.LEFT;
                    textView.setBackgroundResource(R.drawable.bubble_in);
                }
                else {
                    lp2.gravity = Gravity.RIGHT;
                    textView.setBackgroundResource(R.drawable.bubble_out);
                }

                //textView.setTextColor(Color.rgb(200,0,0));
                textView.setLayoutParams(lp2);
                layout.addView(textView);
                scrollView.fullScroll(View.FOCUS_DOWN);
            }

        });




    }


}
