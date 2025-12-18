package mei.family.hymnal;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class SearchActivity extends AppCompatActivity {
	
	private HashMap<String, Object> data = new HashMap<>();
	private String save = "";
	private double length = 0;
	private double r = 0;
	private String value1 = "";
	
	private ArrayList<HashMap<String, Object>> datalist = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private EditText edittext1;
	
	private Intent go = new Intent();
	private SharedPreferences mohisham;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.search);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		edittext1 = findViewById(R.id.edittext1);
		mohisham = getSharedPreferences("mohisham", Activity.MODE_PRIVATE);
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				datalist = new Gson().fromJson(save, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				length = datalist.size();
				r = length - 1;
				for(int _repeat17 = 0; _repeat17 < (int)(length); _repeat17++) {
					value1 = datalist.get((int)r).get("title").toString().concat(datalist.get((int)r).get("number").toString());
					if (!(_charSeq.length() > value1.length()) && value1.toLowerCase().contains(_charSeq.toLowerCase())) {
						
					} else {
						datalist.remove((int)(r));
						listview1.setAdapter(new Listview1Adapter(datalist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					r--;
				}
				listview1.setAdapter(new Listview1Adapter(datalist));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		listview1.setTextFilterEnabled(true);
		listview1.setFastScrollEnabled(true);
		_GettingData();
		listview1.setAdapter(new Listview1Adapter(datalist));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		save = new Gson().toJson(datalist);
	}
	
	public String _loadFromAsset() {
		String result = null;
		try {
			InputStream is = getAssets().open("song.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			result = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return result;
		
	}
	
	
	public void _GettingData() {
		try {
			JSONObject obj = new JSONObject(_loadFromAsset());
			JSONArray countryArray = obj.getJSONArray("countries");
			
			for (int i = 0; i < (int)(countryArray.length()); i++) {
				try {
					JSONObject userDetail = countryArray.getJSONObject(i);
					String title = userDetail.getString("title");
					String song = userDetail.getString("song");
					String number = userDetail.getString("number");
					_AddingSongtoList(number, title, song);
				} catch (Exception e) {
					
				}
			}
			listview1.setAdapter(new Listview1Adapter(datalist));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		} catch (Exception e) {
			
		}
	}
	
	
	public void _AddingSongtoList(final String _number, final String _title, final String _song) {
		String number = _number;
		String title = _title;
		String song = _song;
		data = new HashMap<>();
		data.put("number", _number);
		data.put("title", _title);
		data.put("song", _song);
		datalist.add(data);
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.songs, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView title = _view.findViewById(R.id.title);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView number = _view.findViewById(R.id.number);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			
			ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
			fade_in.setDuration(300);
			fade_in.setFillAfter(true);
			linear1.startAnimation(fade_in);
			title.setText(_data.get((int)_position).get("title").toString());
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/uni.ttf"), 0);
			number.setText(_data.get((int)_position).get("number").toString());
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
					fade_in.setDuration(300);
					fade_in.setFillAfter(true);
					linear1.startAnimation(fade_in);
					go.putExtra("title", _data.get((int)_position).get("title").toString());
					go.putExtra("body", _data.get((int)_position).get("song").toString());
					go.setClass(getApplicationContext(), SongActivity.class);
					startActivity(go);
				}
			});
			if (mohisham.getString("mohisham", "").equals("Amiri")) {
				title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/zaw.ttf"), 0);
			} else {
				if (mohisham.getString("mohisham", "").equals("mohisham")) {
					
				}
			}
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}