package cat.udl.eps.fragments.ejflexible;
 
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
 
public class DetailActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        
           DetailFrag detalle =
              (DetailFrag)getFragmentManager()
                .findFragmentById(R.id.frag_capt);
 
           detalle.mostrarDetalle(
              getIntent().getStringExtra("value"));
        
    }
}