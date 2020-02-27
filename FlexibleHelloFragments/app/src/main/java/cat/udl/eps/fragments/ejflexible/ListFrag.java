package cat.udl.eps.fragments.ejflexible;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFrag extends ListFragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] { "Enterprise", "Star Trek", "Next Generation", "Deep Space 9", "Voyager"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		DetailFrag frag = (DetailFrag) getFragmentManager().findFragmentById(R.id.frag_capt);
		if (frag != null && frag.isInLayout()) {
			frag.mostrarDetalle(getCapt(item));
		}
		else {
			Intent i = new Intent(getActivity(), DetailActivity.class);
			i.putExtra("value", getCapt(item));
			startActivity(i);
		}
	}
	
	private String getCapt(String ship) {
		if (ship.toLowerCase().contains("enterprise")) {
			return "Johnathan Archer";
		}
		if (ship.toLowerCase().contains("star trek")) {
			return "James T. Kirk";
		}
		if (ship.toLowerCase().contains("next generation")) {
			return "Jean-Luc Picard";
		}
		if (ship.toLowerCase().contains("deep space 9")) {
			return "Benjamin Sisko";
		}
		if (ship.toLowerCase().contains("voyager")) {
			return "Kathryn Janeway";
		}
		return "???";
	}
}
