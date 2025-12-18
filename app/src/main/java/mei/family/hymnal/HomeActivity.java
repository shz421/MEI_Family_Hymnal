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
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private HashMap<String, Object> m = new HashMap<>();
	private String FONT = "";
	private double search = 0;
	private double length = 0;
	private String value = "";
	private double number = 0;
	private double can_go_back = 0;
	private HashMap<String, Object> data = new HashMap<>();
	private double r = 0;
	private String value1 = "";
	private String save = "";
	private String url = "";
	private String English = "";
	private String ekode = "";
	private String enama = "";
	private String ejumlah = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> datalist = new ArrayList<>();
	
	private LinearLayout linear_status_bar;
	private LinearLayout linear2;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private TextView textview1;
	private LinearLayout linear8;
	private LinearLayout linear7;
	private ImageView imageview1;
	private ImageView imageview3;
	private ImageView imageview2;
	private LinearLayout linear_gone;
	private ListView listview1;
	private ScrollView _drawer_vscroll1;
	private LinearLayout _drawer_linear1;
	private ImageView _drawer_imageview1;
	private LinearLayout _drawer_linear2;
	private LinearLayout _drawer_linear3;
	private ImageView _drawer_imageview2;
	private TextView _drawer_textview1;
	private ImageView _drawer_imageview3;
	private TextView _drawer_textview2;
	
	private Intent go = new Intent();
	private TimerTask t;
	private RequestNetwork internet;
	private RequestNetwork.RequestListener _internet_request_listener;
	private SharedPreferences mohisham;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear_status_bar = findViewById(R.id.linear_status_bar);
		linear2 = findViewById(R.id.linear2);
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		linear8 = findViewById(R.id.linear8);
		linear7 = findViewById(R.id.linear7);
		imageview1 = findViewById(R.id.imageview1);
		imageview3 = findViewById(R.id.imageview3);
		imageview2 = findViewById(R.id.imageview2);
		linear_gone = findViewById(R.id.linear_gone);
		listview1 = findViewById(R.id.listview1);
		_drawer_vscroll1 = _nav_view.findViewById(R.id.vscroll1);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_imageview1 = _nav_view.findViewById(R.id.imageview1);
		_drawer_linear2 = _nav_view.findViewById(R.id.linear2);
		_drawer_linear3 = _nav_view.findViewById(R.id.linear3);
		_drawer_imageview2 = _nav_view.findViewById(R.id.imageview2);
		_drawer_textview1 = _nav_view.findViewById(R.id.textview1);
		_drawer_imageview3 = _nav_view.findViewById(R.id.imageview3);
		_drawer_textview2 = _nav_view.findViewById(R.id.textview2);
		internet = new RequestNetwork(this);
		mohisham = getSharedPreferences("mohisham", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				go.setClass(getApplicationContext(), SearchActivity.class);
				startActivity(go);
			}
		});
		
		_internet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = HomeActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF29B6F6);
		}
		getSupportActionBar().hide();
		_setFullscreen();
		Window w = HomeActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#3F51B5"));
		listview1.setTextFilterEnabled(true);
		listview1.setFastScrollEnabled(true);
		_GettingData();
		listview1.setAdapter(new Listview1Adapter(datalist));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		linear_gone.setVisibility(View.GONE);
		save = new Gson().toJson(datalist);
		search = 0;
		_advancedCorners(linear2, "#EF5350", 0, 0, 30, 30);
		_setElevation(linear2, 5);
		listview1.setHorizontalScrollBarEnabled(false);
		listview1.setVerticalScrollBarEnabled(false);
		listview1.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		Animation animation;
		animation = AnimationUtils.loadAnimation(
		getApplicationContext(), android.R.anim.slide_in_left);
		animation.setDuration(300); linear1.startAnimation(animation); animation = null;
		listview1.setDivider(null); 
		listview1.setDividerHeight(0);
		if (mohisham.getString("mohisham", "").equals("Amiri")) {
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/zaw.ttf"), 1);
		} else {
			if (mohisham.getString("mohisham", "").equals("mohisham")) {
				textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/uni.ttf"), 1);
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	public void _ActivityFont_(final String _name) {
		FONT = "fonts/".concat(_name.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
		
	} private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface typeace = Typeface.createFromAsset(getAssets(), FONT);;
			
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}catch(Exception e) {
			showMessage(e.toString());
		};
	}
	
	
	public void _advancedCorners(final View _view, final String _color, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n4,(int)_n4,(int)_n3,(int)_n3});
		
		_view.setBackground(gd);
	}
	
	
	public void _setElevation(final View _v, final double _n) {
		_v.setElevation((float) _n);
	}
	
	
	public void _Ripple(final View _v) {
		int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
		android.content.res.TypedArray typedArray = this.obtainStyledAttributes(attrs);
		int backgroundResource = typedArray.getResourceId(0, 0);
		_v.setBackgroundResource(backgroundResource);
		
		_v.setClickable(true);
	}
	
	
	public void _SetRadiusToView(final View _view, final double _radius, final String _Colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); gd.setColor(Color.parseColor(_Colour)); gd.setCornerRadius((int)_radius); _view.setBackground(gd);
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
	
	
	public void _setFullscreen() {
		try {
			Window w = this.getWindow();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
				w.getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
			}
			
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.setStatusBarColor(Color.TRANSPARENT);
			
			w.getDecorView().setSystemUiVisibility(
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // Forces black icons on the status bar
			);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			
			_advancedCorners(linear1, "#FFFFFF", 10, 10, 10, 10);
			_SetRadiusToView(linear2, 100, "#E0E0E0");
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
			_setElevation(linear1, 5);
			if (mohisham.getString("mohisham", "").equals("Amiri")) {
				title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/zaw.ttf"), 0);
			} else {
				if (mohisham.getString("mohisham", "").equals("mohisham")) {
					title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/uni.ttf"), 0);
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