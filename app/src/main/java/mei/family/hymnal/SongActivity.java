package mei.family.hymnal;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SongActivity extends AppCompatActivity {
	
	private FloatingActionButton _fab;
	private double zoom = 0;
	private double search = 0;
	private String a = "";
	private String b = "";
	private String FONT = "";
	
	private LinearLayout linear_status_bar;
	private LinearLayout linear6;
	private LinearLayout linear_storie;
	private TextView textview1;
	private ScrollView vscroll1;
	private TextView textview2;
	
	private SharedPreferences color;
	private SharedPreferences background;
	private SharedPreferences mohisham;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.song);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		linear_status_bar = findViewById(R.id.linear_status_bar);
		linear6 = findViewById(R.id.linear6);
		linear_storie = findViewById(R.id.linear_storie);
		textview1 = findViewById(R.id.textview1);
		vscroll1 = findViewById(R.id.vscroll1);
		textview2 = findViewById(R.id.textview2);
		color = getSharedPreferences("color", Activity.MODE_PRIVATE);
		background = getSharedPreferences("background", Activity.MODE_PRIVATE);
		mohisham = getSharedPreferences("mohisham", Activity.MODE_PRIVATE);
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
				fade_in.setDuration(300);
				fade_in.setFillAfter(true);
				_fab.startAnimation(fade_in);
				if (_fab.getRotation()==0) {
					_showCustom(true);
				} else {
					_showCustom(false);
				};
			}
		});
	}
	
	private void initializeLogic() {
		Window w = SongActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#FFFFFF"));
		vscroll1.setHorizontalScrollBarEnabled(false);
		vscroll1.setVerticalScrollBarEnabled(false);
		vscroll1.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		textview1.setText(getIntent().getStringExtra("title"));
		textview2.setText(getIntent().getStringExtra("body"));
		textview1.setTextSize((int)19);
		zoom = 15;
		search = 0;
		//All buttom sheet commands are repeated twice. Please be careful and keep a backup copy when modifying more block FAB_settings//
		_Fabs_Settings();
		_font();
		if (mohisham.getString("mohisham", "").equals("Amiri")) {
			
		} else {
			if (mohisham.getString("mohisham", "").equals("mohisham")) {
				
			}
		}
		_text_color();
		if (color.getString("mohisham", "").equals("blue")) {
			
		} else {
			if (color.getString("mohisham", "").equals("red")) {
				
			}
			if (color.getString("mohisham", "").equals("green")) {
				
			}
			if (color.getString("mohisham", "").equals("white")) {
				
			}
		}
		_background_color();
		if (background.getString("mohisham", "").equals("brown")) {
			
		} else {
			if (background.getString("mohisham", "").equals("grey")) {
				
			}
			if (background.getString("mohisham", "").equals("white")) {
				
			}
			if (background.getString("mohisham", "").equals("purple")) {
				
			}
		}
		//The default font and text color and background color appears for the first time when the app is launched//
		final ObjectAnimator animator = ObjectAnimator.ofInt(_fab, "backgroundTint", Color.rgb(0, 121, 107), Color.rgb(103, 58, 183));
		animator.setDuration(2000L);
		animator.setEvaluator(new ArgbEvaluator());
		animator.setInterpolator(new DecelerateInterpolator(2));
		animator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int animatedValue = (int) animation.getAnimatedValue();
				_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(animatedValue));
			}
		});
		animator.start();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//If you don't want to save data, delete these blocks//
		_font();
		_text_color();
		_background_color();
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
	}
	public void _advancedCorners(final View _view, final String _color, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n4,(int)_n4,(int)_n3,(int)_n3});
		
		_view.setBackground(gd);
	}
	
	
	public void _Ripple(final View _v) {
		int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
		android.content.res.TypedArray typedArray = this.obtainStyledAttributes(attrs);
		int backgroundResource = typedArray.getResourceId(0, 0);
		_v.setBackgroundResource(backgroundResource);
		
		_v.setClickable(true);
	}
	
	
	public void _showCustom(final boolean _show) {
		_fab.clearAnimation();
		linFab1.clearAnimation();
		linFab2.clearAnimation();
		linFab3.clearAnimation();
		if (_show) {
			_fab.animate().setDuration(100).rotation(45);
			linFab1.setVisibility(View.VISIBLE);
			linFab2.setVisibility(View.VISIBLE);
			linFab3.setVisibility(View.VISIBLE);
			linFab1.animate().setDuration(100).alpha(1f).translationY(0).withEndAction(new Runnable() {
				@Override public void run() {
					
					linFab2.animate().setDuration(100).alpha(1f).translationY(0).withEndAction(new Runnable() {
						@Override public void run() {
							
							linFab3.animate().setDuration(100).alpha(1f).translationY(0);
							
						}
					});
					
				}
			});
		} else {
			_fab.animate().setDuration(100).rotation(0);
			linFab3.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
				@Override public void run() {
					
					linFab2.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
						@Override public void run() {
							
							linFab1.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
								@Override public void run() {
									
									linFab1.setVisibility(View.GONE);
									linFab2.setVisibility(View.GONE);
									linFab3.setVisibility(View.GONE);
									
								}
							});
							
						}
					});
					
				}
			});
		}
	}
	
	
	public void _removeView(final View _view) {
		if (_view.getParent() != null) ((ViewGroup)_view.getParent()).removeView(_view);
	}
	
	
	public void _Fabs_Settings() {
		View cv = getLayoutInflater().inflate(R.layout.custom_fabs_view, null);
		
		linFab1 = (LinearLayout)cv.findViewById(R.id.lin1);
		linFab2 = (LinearLayout)cv.findViewById(R.id.lin2);
		linFab3 = (LinearLayout)cv.findViewById(R.id.lin3);
		
		textFab1 = (TextView)cv.findViewById(R.id.textview1);
		textFab2 = (TextView)cv.findViewById(R.id.textview2);
		textFab3 = (TextView)cv.findViewById(R.id.textview3);
		
		imgFab1 = (ImageView)cv.findViewById(R.id.imageview1);
		imgFab2 = (ImageView)cv.findViewById(R.id.imageview2);
		imgFab3 = (ImageView)cv.findViewById(R.id.imageview3);
		
		final LinearLayout l1 = (LinearLayout)cv.findViewById(R.id.linear1);
		
		_removeView(l1);
		
		((ViewGroup)_fab.getParent()).addView(l1);
		_setup(textFab1, "#FFFFFF");
		_setup(textFab2, "#FFFFFF");
		_setup(textFab3, "#FFFFFF");
		
		_setup(imgFab1, "#FFFFFF");
		_setup(imgFab2, "#FFFFFF");
		_setup(imgFab3, "#FFFFFF");
		textFab1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
		textFab2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
		textFab3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
		textFab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(SongActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.settings,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
				
				TextView t3 = (TextView) bottomSheetView.findViewById(R.id.t3);
				
				TextView t4 = (TextView) bottomSheetView.findViewById(R.id.t4);
				
				TextView text1 = (TextView) bottomSheetView.findViewById(R.id.text1);
				
				TextView text4 = (TextView) bottomSheetView.findViewById(R.id.text4);
				
				TextView bzin = (TextView) bottomSheetView.findViewById(R.id.bzin);
				
				TextView bzout = (TextView) bottomSheetView.findViewById(R.id.bzout);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout bg2 = (LinearLayout) bottomSheetView.findViewById(R.id.bg2);
				
				LinearLayout bg3 = (LinearLayout) bottomSheetView.findViewById(R.id.bg3);
				
				LinearLayout bg4 = (LinearLayout) bottomSheetView.findViewById(R.id.bg4);
				
				LinearLayout color1 = (LinearLayout) bottomSheetView.findViewById(R.id.color1);
				
				LinearLayout color2 = (LinearLayout) bottomSheetView.findViewById(R.id.color2);
				
				LinearLayout color3 = (LinearLayout) bottomSheetView.findViewById(R.id.color3);
				
				LinearLayout color4 = (LinearLayout) bottomSheetView.findViewById(R.id.color4);
				
				LinearLayout color5 = (LinearLayout) bottomSheetView.findViewById(R.id.color5);
				
				LinearLayout back1 = (LinearLayout) bottomSheetView.findViewById(R.id.back1);
				
				LinearLayout back2 = (LinearLayout) bottomSheetView.findViewById(R.id.back2);
				
				LinearLayout back3 = (LinearLayout) bottomSheetView.findViewById(R.id.back3);
				
				LinearLayout back4 = (LinearLayout) bottomSheetView.findViewById(R.id.back4);
				
				LinearLayout back5 = (LinearLayout) bottomSheetView.findViewById(R.id.back5);
				
				LinearLayout font1 = (LinearLayout) bottomSheetView.findViewById(R.id.font1);
				
				LinearLayout font4 = (LinearLayout) bottomSheetView.findViewById(R.id.font4);
				
				LinearLayout all = (LinearLayout) bottomSheetView.findViewById(R.id.all);
				_advancedCorners(bzout, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(bzin, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(bg, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(bg2, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(bg3, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(bg4, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(color1, "#2196F3", 30, 30, 30, 30);
				_advancedCorners(color2, "#4CAF50", 30, 30, 30, 30);
				_advancedCorners(color3, "#F44336", 30, 30, 30, 30);
				_advancedCorners(color4, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(color5, "#000000", 30, 30, 30, 30);
				_advancedCorners(back1, "#795548", 30, 30, 30, 30);
				_advancedCorners(back2, "#607D8B", 30, 30, 30, 30);
				_advancedCorners(back3, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(back5, "#2196F3", 30, 30, 30, 30);
				back4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)1, 0xFFFFFFFF, 0xFF673AB7));
				_advancedCorners(font1, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(font4, "#FFFFFF", 30, 30, 30, 30);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				bzin.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				bzout.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				text4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t1.setText("Text Size");
				t2.setText("Text Color Selection");
				t3.setText("Background Color Selection");
				t4.setText("Text Font Selection");
				color1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "blue").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Blue");
					}
				});
				color2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "green").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Green");
					}
				});
				color3.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "red").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Red");
					}
				});
				color4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "white").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to White");
					}
				});
				color5.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "black").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Black");
					}
				});
				back1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "brown").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Brown");
					}
				});
				back2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "grey").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Grey");
					}
				});
				back3.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "white").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to White");
					}
				});
				back4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "purple").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Purple");
					}
				});
				back5.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "Blue").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to blue");
					}
				});
				text1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						mohisham.edit().putString("mohisham", "Amiri").commit();
						_font();
						SketchwareUtil.showMessage(getApplicationContext(), "Font changed to Zawgyi");
					}
				});
				text4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						mohisham.edit().putString("mohisham", "mohisham").commit();
						_font();
						SketchwareUtil.showMessage(getApplicationContext(), "Font changed to Unicode");
					}
				});
				bzin.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						if (zoom < 35) {
							zoom++;
							textview2.setTextSize((float)zoom);
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "bigger size");
						}
					}
				});
				bzout.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						if (zoom > 14) {
							zoom--;
							textview2.setTextSize((float)zoom);
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "smallest size");
						}
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
				_showCustom(false);
			}
		});
		imgFab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(SongActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.settings,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
				
				TextView t3 = (TextView) bottomSheetView.findViewById(R.id.t3);
				
				TextView t4 = (TextView) bottomSheetView.findViewById(R.id.t4);
				
				TextView text1 = (TextView) bottomSheetView.findViewById(R.id.text1);
				
				TextView text4 = (TextView) bottomSheetView.findViewById(R.id.text4);
				
				TextView bzin = (TextView) bottomSheetView.findViewById(R.id.bzin);
				
				TextView bzout = (TextView) bottomSheetView.findViewById(R.id.bzout);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout bg2 = (LinearLayout) bottomSheetView.findViewById(R.id.bg2);
				
				LinearLayout bg3 = (LinearLayout) bottomSheetView.findViewById(R.id.bg3);
				
				LinearLayout bg4 = (LinearLayout) bottomSheetView.findViewById(R.id.bg4);
				
				LinearLayout color1 = (LinearLayout) bottomSheetView.findViewById(R.id.color1);
				
				LinearLayout color2 = (LinearLayout) bottomSheetView.findViewById(R.id.color2);
				
				LinearLayout color3 = (LinearLayout) bottomSheetView.findViewById(R.id.color3);
				
				LinearLayout color4 = (LinearLayout) bottomSheetView.findViewById(R.id.color4);
				
				LinearLayout color5 = (LinearLayout) bottomSheetView.findViewById(R.id.color5);
				
				LinearLayout back1 = (LinearLayout) bottomSheetView.findViewById(R.id.back1);
				
				LinearLayout back2 = (LinearLayout) bottomSheetView.findViewById(R.id.back2);
				
				LinearLayout back3 = (LinearLayout) bottomSheetView.findViewById(R.id.back3);
				
				LinearLayout back4 = (LinearLayout) bottomSheetView.findViewById(R.id.back4);
				
				LinearLayout back5 = (LinearLayout) bottomSheetView.findViewById(R.id.back5);
				
				LinearLayout font1 = (LinearLayout) bottomSheetView.findViewById(R.id.font1);
				
				LinearLayout font4 = (LinearLayout) bottomSheetView.findViewById(R.id.font4);
				
				LinearLayout all = (LinearLayout) bottomSheetView.findViewById(R.id.all);
				_advancedCorners(bzout, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(bzin, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(bg, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(bg2, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(bg3, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(bg4, "#673AB7", 30, 30, 30, 30);
				_advancedCorners(color1, "#2196F3", 30, 30, 30, 30);
				_advancedCorners(color2, "#4CAF50", 30, 30, 30, 30);
				_advancedCorners(color3, "#F44336", 30, 30, 30, 30);
				_advancedCorners(color4, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(color5, "#000000", 30, 30, 30, 30);
				_advancedCorners(back1, "#795548", 30, 30, 30, 30);
				_advancedCorners(back2, "#607D8B", 30, 30, 30, 30);
				_advancedCorners(back3, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(back5, "#2196F3", 30, 30, 30, 30);
				back4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)1, 0xFFFFFFFF, 0xFF673AB7));
				_advancedCorners(font1, "#FFFFFF", 30, 30, 30, 30);
				_advancedCorners(font4, "#FFFFFF", 30, 30, 30, 30);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				bzin.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				bzout.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				text4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mue.ttf"), 1);
				t1.setText("Text Size");
				t2.setText("Text Color Selection");
				t3.setText("Background Color Selection");
				t4.setText("Text Font Selection");
				color1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "blue").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Blue");
					}
				});
				color2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "green").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Green");
					}
				});
				color3.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "red").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Red");
					}
				});
				color4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "white").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to White");
					}
				});
				color5.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						color.edit().putString("mohisham", "black").commit();
						_text_color();
						SketchwareUtil.showMessage(getApplicationContext(), "text color changed to Black");
					}
				});
				back1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "brown").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Brown");
					}
				});
				back2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "grey").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Blue Grey");
					}
				});
				back3.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "white").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to White");
					}
				});
				back4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "purple").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Purple");
					}
				});
				back5.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						background.edit().putString("mohisham", "Blue").commit();
						_background_color();
						SketchwareUtil.showMessage(getApplicationContext(), "Background color changed to Blue");
					}
				});
				text1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						mohisham.edit().putString("mohisham", "Amiri").commit();
						_font();
						SketchwareUtil.showMessage(getApplicationContext(), "Font changed to Zawgyi");
					}
				});
				text4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						mohisham.edit().putString("mohisham", "mohisham").commit();
						_font();
						SketchwareUtil.showMessage(getApplicationContext(), "Font changed to Unicode");
					}
				});
				bzin.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						if (zoom < 35) {
							zoom++;
							textview2.setTextSize((float)zoom);
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "bigger size");
						}
					}
				});
				bzout.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						if (zoom > 14) {
							zoom--;
							textview2.setTextSize((float)zoom);
						} else {
							SketchwareUtil.showMessage(getApplicationContext(), "smallest size");
						}
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
				_showCustom(false);
			}
		});
		textFab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString().concat(textview2.getText().toString())));
				SketchwareUtil.showMessage(getApplicationContext(), "copied");
				_showCustom(false);
			}
		});
		imgFab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString().concat(textview2.getText().toString())));
				SketchwareUtil.showMessage(getApplicationContext(), "copied");
				_showCustom(false);
			}
		});
		textFab3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				a = textview1.getText().toString();
				b = textview2.getText().toString();
				Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_SUBJECT, a); i.putExtra(android.content.Intent.EXTRA_TEXT, b); startActivity(Intent.createChooser(i,"Share using"));
				_showCustom(false);
			}
		});
		imgFab3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				a = textview1.getText().toString();
				b = textview2.getText().toString();
				Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_SUBJECT, a); i.putExtra(android.content.Intent.EXTRA_TEXT, b); startActivity(Intent.createChooser(i,"Share using"));
				_showCustom(false);
			}
		});
		linFab1.setTranslationY(getDip(50));
		linFab1.setAlpha(0);
		linFab2.setTranslationY(getDip(50));
		linFab2.setAlpha(0);
		linFab3.setTranslationY(getDip(50));
		linFab3.setAlpha(0);
	}
	
	
	public void _setup(final View _a, final String _b) {
		_setRipple(_a, _b, SketchwareUtil.getDip(getApplicationContext(), (int)(18)), "#FFFFFF");
		_a.setElevation(4f);
	}
	
	
	public void _init() {
	}
	
	private LinearLayout linFab1, linFab2, linFab3;
	
	private TextView textFab1, textFab2, textFab3;
	
	private ImageView imgFab1, imgFab2, imgFab3;
	
	{
	}
	
	
	public void _font() {
		if (mohisham.getString("mohisham", "").equals("Amiri")) {
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/zaw.ttf"), 1);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/zaw.ttf"), 0);
		} else {
			if (mohisham.getString("mohisham", "").equals("mohisham")) {
				textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/uni.ttf"), 1);
				textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/uni.ttf"), 0);
			}
		}
	}
	
	
	public void _text_color() {
		if (color.getString("mohisham", "").equals("blue")) {
			textview1.setTextColor(0xFF2196F3);
			textview2.setTextColor(0xFF2196F3);
		} else {
			if (color.getString("mohisham", "").equals("red")) {
				textview1.setTextColor(0xFFF44336);
				textview2.setTextColor(0xFFF44336);
			}
			if (color.getString("mohisham", "").equals("green")) {
				textview1.setTextColor(0xFF00C853);
				textview2.setTextColor(0xFF00C853);
			}
			if (color.getString("mohisham", "").equals("white")) {
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
			}
			if (color.getString("mohisham", "").equals("black")) {
				textview1.setTextColor(0xFF000000);
				textview2.setTextColor(0xFF000000);
			}
		}
	}
	
	
	public void _background_color() {
		//Use this site to convert Hex colors to Rbg//
		//https://www.rapidtables.com/convert/color/hex-to-rgb.html//
		if (background.getString("mohisham", "").equals("brown")) {
			Window w = SongActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#795548"));
			_advancedCorners(vscroll1, "#795548", 30, 30, 0, 0);
			_advancedCorners(linear6, "#795548", 0, 0, 30, 30);
			final ObjectAnimator animator = ObjectAnimator.ofInt(_fab, "backgroundTint", Color.rgb(0, 121, 107), Color.rgb(121, 85, 72));
			animator.setDuration(2000L);
			animator.setEvaluator(new ArgbEvaluator());
			animator.setInterpolator(new DecelerateInterpolator(2));
			animator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					int animatedValue = (int) animation.getAnimatedValue();
					_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(animatedValue));
				}
			});
			animator.start();
		} else {
			if (background.getString("mohisham", "").equals("grey")) {
				Window w = SongActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#607D8B"));
				_advancedCorners(vscroll1, "#607D8B", 30, 30, 0, 0);
				_advancedCorners(linear6, "#607D8B", 0, 0, 30, 30);
				final ObjectAnimator animator = ObjectAnimator.ofInt(_fab, "backgroundTint", Color.rgb(0, 121, 107), Color.rgb(96, 125, 139));
				animator.setDuration(2000L);
				animator.setEvaluator(new ArgbEvaluator());
				animator.setInterpolator(new DecelerateInterpolator(2));
				animator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int animatedValue = (int) animation.getAnimatedValue();
						_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(animatedValue));
					}
				});
				animator.start();
			}
			if (background.getString("mohisham", "").equals("white")) {
				Window w = SongActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#FFFFFF"));
				_advancedCorners(vscroll1, "#FFFFFF", 30, 30, 0, 0);
				_advancedCorners(linear6, "#FFFFFF", 0, 0, 30, 30);
				final ObjectAnimator animator = ObjectAnimator.ofInt(_fab, "backgroundTint", Color.rgb(0, 121, 107), Color.rgb(238, 238, 238));
				animator.setDuration(2000L);
				animator.setEvaluator(new ArgbEvaluator());
				animator.setInterpolator(new DecelerateInterpolator(2));
				animator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int animatedValue = (int) animation.getAnimatedValue();
						_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(animatedValue));
					}
				});
				animator.start();
			}
			if (background.getString("mohisham", "").equals("purple")) {
				Window w = SongActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#673AB7"));
				_advancedCorners(vscroll1, "#673AB7", 30, 30, 0, 0);
				_advancedCorners(linear6, "#673AB7", 0, 0, 30, 30);
				final ObjectAnimator animator = ObjectAnimator.ofInt(_fab, "backgroundTint", Color.rgb(0, 121, 107), Color.rgb(103, 58, 183));
				animator.setDuration(2000L);
				animator.setEvaluator(new ArgbEvaluator());
				animator.setInterpolator(new DecelerateInterpolator(2));
				animator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int animatedValue = (int) animation.getAnimatedValue();
						_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(animatedValue));
					}
				});
				animator.start();
			}
			if (background.getString("mohisham", "").equals("Blue")) {
				Window w = SongActivity.this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#2196F3"));
				_advancedCorners(vscroll1, "#2196F3", 30, 30, 0, 0);
				_advancedCorners(linear6, "#2196F3", 0, 0, 30, 30);
				final ObjectAnimator animator = ObjectAnimator.ofInt(_fab, "backgroundTint", Color.rgb(0, 121, 107), Color.rgb(103, 58, 183));
				animator.setDuration(2000L);
				animator.setEvaluator(new ArgbEvaluator());
				animator.setInterpolator(new DecelerateInterpolator(2));
				animator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int animatedValue = (int) animation.getAnimatedValue();
						_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(animatedValue));
					}
				});
				animator.start();
			}
		}
	}
	
	
	public void _setRipple(final View _a, final String _b, final double _c, final String _d) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_b));
		gd.setCornerRadius((float)_c);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_d)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_a.setClickable(true);
		_a.setClipToOutline(true);
		_a.setBackground(ripdrb);
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