package xcini.rv.com.xcini;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xcini.rv.com.xcini.Utils.CommonUtils;

public class Account extends Fragment {


    @OnClick (R.id.btnSignOut)
    public void onClickSignOut(){
        Intent it = new Intent(getActivity(),Login.class);
        startActivity(it);
        getActivity().finishAffinity();

        CommonUtils.Companion.clearPrf(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_account, container, false);

        ButterKnife.bind(this,view);

        return view;

    }

}
