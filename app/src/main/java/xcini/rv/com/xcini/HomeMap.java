package xcini.rv.com.xcini;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class HomeMap extends Fragment {
    LinearLayout l;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_map, container, false);
          l =(LinearLayout) root.findViewById(R.id.l);

        l.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("x:", motionEvent.getX()+"");
                Log.d("y:", motionEvent.getY()+"");
                return false;
            }
        });



        return root;
    }

}
