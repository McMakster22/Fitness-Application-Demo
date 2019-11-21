package dk.now.just.bogym.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.now.just.bogym.Model.RoutineObjectItem;
import dk.now.just.bogym.R;

public class RoutineAdapter extends ArrayAdapter<RoutineObjectItem>{

    private  Context mContext;
    private List<RoutineObjectItem> routineIlists = new ArrayList<>();

    public RoutineAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<RoutineObjectItem> list) {
        super(context, 0, list);
        mContext = context;
        routineIlists = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_routine,parent,false);

        RoutineObjectItem currentRoutine = routineIlists.get(position);

        TextView muscleGroup = (TextView) listItem.findViewById(R.id.muscleType);
        muscleGroup.setText(currentRoutine.getMuscleGroup());

        TextView dayOfWeek = (TextView) listItem.findViewById(R.id.dayOfWeek);
        dayOfWeek.setText(currentRoutine.getWeekOfTheDay());

        TextView exercise = listItem.findViewById(R.id.Ex1);
        exercise.setText(currentRoutine.getExercise());

        TextView set = listItem.findViewById(R.id.Nset1);
        set.setText(currentRoutine.getSet());

        TextView rep = listItem.findViewById(R.id.Nrep1);
        rep.setText(currentRoutine.getRep());




        return listItem;
    }
}



