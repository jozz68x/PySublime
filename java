package com.inswall.android.ui.fragment;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.inswall.android.helper.Constants;
import com.inswall.android.ui.dialog.DefaultDialog;
import com.inswall.android.ui.widget.SplitButtonsTintLayout;
import com.inswall.android.ui.widget.TintWallViewNew;
import com.inswall.library.colorpicker.ColorPickerDialog;
import com.inswall.android.R;
import com.inswall.android.ui.dialog.WallpaperSuccessDialog;
import com.inswall.android.ui.fragment.base.BasePageFragment;
import com.inswall.android.helper.SharedPreferences;
import com.inswall.android.util.Utils;
import com.inswall.android.ui.widget.TintWallView;
import com.inswall.library.colorpicker.helpers.MaterialColorPalette;
import com.inswall.library.colorpicker.model.ColorItem;
import com.inswall.library.colorpicker.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Jose Diaz (@Diaz18x)
 *         <p>
 *         IMPLEMENTATION FUTURE: https://github.com/glesik/wpgen/blob/master/app/src/main/java/net/glsk/wpgen/ColorsActivity.java
 */

public class TintWallFragment extends BasePageFragment implements View.OnClickListener, View.OnLongClickListener {

    public static final String TAG = TintWallFragment.class.getSimpleName();


    public static final int SELECT_COLOR_ONE = 1;
    public static final int SELECT_COLOR_TWO = 2;
    public static final int SELECT_COLOR_THREE = 3;

    public static final int POSITION_LIST_COLOR_ONE = 0;
    public static final int POSITION_LIST_COLOR_TWO = 1;
    public static final int POSITION_LIST_COLOR_THREE = 2;

    public static final int SELECT_TYPE_GRADIENT_VERTICAL = 0;
    public static final int SELECT_TYPE_GRADIENT_DIAGONAL = 1;
    public static final int SELECT_TYPE_GRADIENT_PLASMA = 2;
    public static final int SELECT_TYPE_STRIPES = 3;

    private Context mContext;
    SharedPreferences mSharedPreferences;

    private TintWallViewNew mTintView;
    private Button mButtonStart;
    private Button mButtonEnd;

    private SplitButtonsTintLayout mTintButtons;

    final private ArrayList<Integer> mColorsStartRandom = new ArrayList<>();
    final private ArrayList<Integer> mColorsEndRandom = new ArrayList<>();

    private ArrayList<String> mColorsList = new ArrayList<>();

    private int mSelectColors;
    private int mSelectTypes;

    public TintWallFragment() {
    }

    @Override
    public int getTitle() {
        return R.string.tintwall;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        for (int color : MaterialColorPalette.PALETTE_RED()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_PINK()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_PURPLE()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_DEEPPURPLE()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_INDIGO()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_BLUE()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_LIGHTBLUE()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_CYAN()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_TEAL()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_GREEN()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_LIGHTGREEN()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_LIME()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_YELLOW()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_AMBER()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_ORANGE()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_DEEPORANGE()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_BROWN()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_GREY()) {
            mColorsStartRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_BLUEGRAY()) {
            mColorsStartRandom.add(color);
        }
        mColorsStartRandom.add(getResources().getColor(R.color.successful));
        mColorsStartRandom.add(getResources().getColor(R.color.accent_1_dark));
        mColorsStartRandom.add(getResources().getColor(R.color.accent_dark));


        for (int color : MaterialColorPalette.PALETTE_RED()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_PINK()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_PURPLE()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_DEEPPURPLE()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_INDIGO()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_BLUE()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_LIGHTBLUE()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_CYAN()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_TEAL()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_GREEN()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_LIGHTGREEN()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_LIME()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_YELLOW()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_AMBER()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_ORANGE()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_DEEPORANGE()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_BROWN()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_GREY()) {
            mColorsEndRandom.add(color);
        }
        for (int color : MaterialColorPalette.PALETTE_BLUEGRAY()) {
            mColorsEndRandom.add(color);
        }
        mColorsEndRandom.add(getResources().getColor(R.color.successful));
        mColorsEndRandom.add(getResources().getColor(R.color.accent_1_dark));
        mColorsEndRandom.add(getResources().getColor(R.color.accent_dark));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.material_amber50));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_pink));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_purple));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_deeppurple));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_indigo));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_blue));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_lightblue));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_cyan));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_teal));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_green));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_lightgreen));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_lime));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_yellow));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_amber));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_orange));
//        mColorsStartRandom.add(getActivity().getResources().getColor(R.color.color_deeporange));
//
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_red));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_pink));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_purple));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_deeppurple));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_indigo));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_blue));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_lightblue));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_cyan));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_teal));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_green));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_lightgreen));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_lime));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_yellow));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_amber));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_orange));
//        mColorsEndRandom.add(getActivity().getResources().getColor(R.color.color_deeporange));
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Window window = getActivity().getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tintwall, container, false);
        mContext = view.getContext();

        mSharedPreferences = new SharedPreferences(getActivity().getApplicationContext());

        mSelectColors = mSharedPreferences.getInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_TWO);
        mSelectTypes = mSharedPreferences.getInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL);

        mTintView = (TintWallViewNew) view.findViewById(R.id.wallTintView);
        mTintView.setDrawingCacheEnabled(true);

        ArrayList<String> listString = mSharedPreferences.getArray(Constants.PREF_END_SELECTED_COLOR);
        for (int i = 0; i < listString.size(); i++) {
            mColorsList.add(listString.get(i));
        }
        mTintView.setColorsList(mColorsList);
        mTintView.invalidate();

        mTintButtons = (SplitButtonsTintLayout) view.findViewById(R.id.buttonsFrame);
        setupTintButtons(mSelectColors);
        switch (mSelectColors) {
            case SELECT_COLOR_ONE:
                updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_ONE)).getChildAt(0)), Color.parseColor(mColorsList.get(POSITION_LIST_COLOR_ONE)));
                break;
            case SELECT_COLOR_TWO:
                updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_ONE)).getChildAt(0)), Color.parseColor(mColorsList.get(POSITION_LIST_COLOR_ONE)));

                updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_TWO)).getChildAt(0)), Color.parseColor(mColorsList.get(POSITION_LIST_COLOR_TWO)));
                break;
            case SELECT_COLOR_THREE:
                updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_ONE)).getChildAt(0)), Color.parseColor(mColorsList.get(POSITION_LIST_COLOR_ONE)));

                updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_TWO)).getChildAt(0)), Color.parseColor(mColorsList.get(POSITION_LIST_COLOR_TWO)));

                updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_THREE)).getChildAt(0)), Color.parseColor(mColorsList.get(POSITION_LIST_COLOR_THREE)));
                break;
        }

        ImageButton btn_randomize = (ImageButton) view.findViewById(R.id.btn_randomize);
        ImageButton btn_apply = (ImageButton) view.findViewById(R.id.btn_apply);
        ImageButton btn_rotate = (ImageButton) view.findViewById(R.id.btn_rotate);

        btn_randomize.setOnClickListener(this);
        btn_randomize.setOnLongClickListener(this);
        btn_apply.setOnClickListener(this);
        btn_apply.setOnLongClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_rotate.setOnLongClickListener(this);

        return view;
    }

    private void setupTintButtons(int selectColors) {
        mTintButtons.clear();
        mTintButtons.setButtonCount(selectColors);
        if (!mTintButtons.hasAllButtons()) {
            for (int i = 1; i <= selectColors; i++)
                mTintButtons.addButton(String.valueOf(i));
        }

        for (int i = 0; i < mTintButtons.getChildCount(); i++) {
            ((LinearLayout) mTintButtons.getChildAt(i)).getChildAt(0).setOnClickListener(this);
            ((LinearLayout) mTintButtons.getChildAt(i)).getChildAt(0).setOnLongClickListener(this);
        }
        mTintButtons.invalidate();
    }

    public void updateButtonPressTint(Button button, @ColorInt int color){
        final LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(
                getContext(), R.drawable.circle_image_picker_tint);
        final GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable
                .findDrawableByLayerId(R.id.oval_color);

        gradientDrawable.setColor(color); // change color
        button.setBackground(layerDrawable);
        setTextColorButton(button, color);

        button.invalidate();
    }

    private void setTextColorButton(Button button, @ColorInt int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = Color.alpha(color);

        if (!ColorUtils.isColorLight(Color.argb(alpha, red, green, blue))) {
            button.setTextColor(ColorUtils.adjustAlpha(Color.WHITE, 0.7F));
        } else {
            button.setTextColor(ColorUtils.adjustAlpha(Color.BLACK, 0.6F));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_tintwall, menu);

        SubMenu mColorsSubMenu = menu.findItem(R.id.tintwall_colors).getSubMenu();
        MenuItem color1Item = mColorsSubMenu.getItem(0);
        MenuItem color2Item = mColorsSubMenu.getItem(1);
        MenuItem color3Item = mColorsSubMenu.getItem(2);
        //Checked MenuItem COLORS
        switch (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_TWO)) {
            case SELECT_COLOR_ONE:
                color1Item.setChecked(true);
                break;
            case SELECT_COLOR_TWO:
                color2Item.setChecked(true);
                break;
            case SELECT_COLOR_THREE:
                color3Item.setChecked(true);
                break;
        }

        SubMenu typesSubMenu = menu.findItem(R.id.tintwall_types).getSubMenu();
        MenuItem gradientVerticalItem = typesSubMenu.getItem(0);
        MenuItem gradientDiagonalItem = typesSubMenu.getItem(1);
        MenuItem gradientPlasmaItem = typesSubMenu.getItem(2);
        MenuItem stripesItem = typesSubMenu.getItem(3);
        //Checked MenuItem TYPE
        switch (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL)) {
            case SELECT_TYPE_GRADIENT_VERTICAL:
                gradientVerticalItem.setChecked(true);
                break;
            case SELECT_TYPE_GRADIENT_DIAGONAL:
                gradientDiagonalItem.setChecked(true);
                break;
            case SELECT_TYPE_GRADIENT_PLASMA:
                gradientPlasmaItem.setChecked(true);
                break;
            case SELECT_TYPE_STRIPES:
                stripesItem.setChecked(true);
                break;
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        /*
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            String title = mi.getTitle().toString();
            Spannable newTitle = new SpannableString(title);
            newTitle.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.accent_1_dark)), 0, newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.title(newTitle);
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color1:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_TWO) == SELECT_COLOR_ONE)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_ONE);
                mSelectColors = SELECT_COLOR_ONE;
                setupTintButtons(mSelectColors);
                break;
            case R.id.color2:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_TWO) == SELECT_COLOR_TWO)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_TWO);
                mSelectColors = SELECT_COLOR_TWO;
                setupTintButtons(mSelectColors);
                break;
            case R.id.color3:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_TWO) == SELECT_COLOR_THREE)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_NUMBER_COLORS, SELECT_COLOR_THREE);
                mSelectColors = SELECT_COLOR_THREE;
                setupTintButtons(mSelectColors);
                break;

            case R.id.gradient_vertical:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL) == SELECT_TYPE_GRADIENT_VERTICAL)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL);
                mSelectTypes = SELECT_TYPE_GRADIENT_VERTICAL;
                break;
            case R.id.gradient_diagonal:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL) == SELECT_TYPE_GRADIENT_DIAGONAL)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_DIAGONAL);
                mSelectTypes = SELECT_TYPE_GRADIENT_DIAGONAL;
                break;
            case R.id.gradient_plasma:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL) == SELECT_TYPE_GRADIENT_PLASMA)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_PLASMA);
                mSelectTypes = SELECT_TYPE_GRADIENT_PLASMA;
                break;
            case R.id.stripes:
                if (mSharedPreferences.getInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_GRADIENT_VERTICAL) == SELECT_TYPE_STRIPES)
                    break;
                mSharedPreferences.saveInteger(Constants.PREF_TINTWALL_TYPE, SELECT_TYPE_STRIPES);
                mSelectTypes = SELECT_TYPE_STRIPES;
                break;
        }
        item.setChecked(!item.isChecked());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        /*mSharedPreferences.saveInteger(Constants.PREF_START_SELECTED_COLOR, mTintView.getColorOne());
        mSharedPreferences.saveInteger(Constants.PREF_END_SELECTED_COLOR, mTintView.geColorTwo());*/
        mSharedPreferences.saveArray(Constants.PREF_END_SELECTED_COLOR, mColorsList);
    }

    @Override
    public void onClick(final View view) {
        final LayerDrawable layerDrawableSTART = (LayerDrawable) ContextCompat.getDrawable(
                getActivity(), R.drawable.circle_image_picker_tint);
        final GradientDrawable gradientDrawableSTART = (GradientDrawable) layerDrawableSTART
                .findDrawableByLayerId(R.id.oval_color);

        final LayerDrawable layerDrawableEND = (LayerDrawable) ContextCompat.getDrawable(
                getActivity(), R.drawable.circle_image_picker_tint);
        final GradientDrawable gradientDrawableEND = (GradientDrawable) layerDrawableEND
                .findDrawableByLayerId(R.id.oval_color);

        ArrayList<ColorItem> colorsListPicker = MaterialColorPalette.PALETTE_PRIMARY_CUSTOM();
        colorsListPicker.add(new ColorItem(getResources().getColor(R.color.successful), "Accent Extra"));
        colorsListPicker.add(new ColorItem(getResources().getColor(R.color.accent_1_dark), "Accent Primary"));
        colorsListPicker.add(new ColorItem(getResources().getColor(R.color.accent_dark), "Accent Secondary"));

        if (view.getId() == R.id.btnStart) {
            new ColorPickerDialog.Builder(mContext)
                    .initialColor(mTintView.getColorOne())
                    .colorsPaletteCustom(colorsListPicker)
                    .cancelable(true)
                    .showAlpha(false)
                    .activeAlpha(false)
                    .selectMode(ColorPickerDialog.SelectMode.PALETTE)
                    .backgroundColor(getResources().getColor(R.color.primary_1_dark))
                    .listener(new ColorPickerDialog.OnColorChangedListener() {
                        @Override
                        public void colorChanged(DialogInterface dialog, int selectedColor) {

                            float[] hsv = new float[3];
                            Color.colorToHSV(selectedColor, hsv);

                            mTintView.seColorOne(selectedColor);
                            mTintView.seColorTwo(mTintView.geColorTwo());
                            mTintView.invalidate();

                            gradientDrawableSTART.setColor(mTintView.getColorOne()); // change color
                            mButtonStart.setBackground(layerDrawableSTART);
                            setTextColorButton(mButtonStart, mTintView.getColorOne());

                            gradientDrawableEND.setColor(mTintView.geColorTwo()); // change color
                            mButtonEnd.setBackground(layerDrawableEND);
                            setTextColorButton(mButtonEnd, mTintView.geColorTwo());
                        }
                    })
                    .build().show((((AppCompatActivity) getActivity()).getSupportFragmentManager()), null);
        } else if (view.getId() == R.id.btnEnd) {
            new ColorPickerDialog.Builder(mContext)
                    .initialColor(mTintView.geColorTwo())
                    .colorsPaletteCustom(colorsListPicker)
                    .cancelable(true)
                    .showAlpha(false)
                    .activeAlpha(false)
                    .selectMode(ColorPickerDialog.SelectMode.PALETTE)
                    .backgroundColor(getResources().getColor(R.color.primary_1_dark))
                    .listener(new ColorPickerDialog.OnColorChangedListener() {
                        @Override
                        public void colorChanged(DialogInterface dialog, int selectedColor) {

                            float[] hsv = new float[3];
                            Color.colorToHSV(selectedColor, hsv);

                            mTintView.seColorOne(mTintView.getColorOne());
                            mTintView.seColorTwo(selectedColor);
                            mTintView.invalidate();

                            gradientDrawableSTART.setColor(mTintView.getColorOne()); // change color
                            mButtonStart.setBackground(layerDrawableSTART);
                            setTextColorButton(mButtonStart, mTintView.getColorOne());

                            gradientDrawableEND.setColor(mTintView.geColorTwo()); // change color
                            mButtonEnd.setBackground(layerDrawableEND);
                            setTextColorButton(mButtonEnd, mTintView.geColorTwo());
                        }
                    })
                    .build().show((((AppCompatActivity) getActivity()).getSupportFragmentManager()), null);
        } else if (view.getId() == R.id.btn_randomize) {
            int mColorStart = (int) Math.floor(Math.random() * mColorsStartRandom.size());
            int mColorEnd = (int) Math.floor(Math.random() * mColorsEndRandom.size());

            mTintView.seColorOne(mColorsStartRandom.get(mColorStart));
            mTintView.seColorTwo(mColorsEndRandom.get(mColorEnd));
            mTintView.invalidate();

            gradientDrawableSTART.setColor(mTintView.getColorOne()); // change color
            mButtonStart.setBackground(layerDrawableSTART);
            setTextColorButton(mButtonStart, mTintView.getColorOne());

            gradientDrawableEND.setColor(mTintView.geColorTwo()); // change color
            mButtonEnd.setBackground(layerDrawableEND);
            setTextColorButton(mButtonEnd, mTintView.geColorTwo());

        } else if (view.getId() == R.id.btn_apply) {
            ArrayList<String> colorsListTint = new ArrayList<>();
            colorsListTint.add(ColorUtils.colorIntToColorHex(mTintView.getColorOne(), true));
            colorsListTint.add(ColorUtils.colorIntToColorHex(mTintView.geColorTwo(), true));

            switch (mSelectTypes) {
                case SELECT_TYPE_GRADIENT_VERTICAL:
                    //applyWallpaper(mTintView.getDrawingCache());
                    applyWallpaper(colorsListTint, SET_GRADIENT_VERTICAL);
                    break;
                case SELECT_TYPE_GRADIENT_DIAGONAL:
                    applyWallpaper(colorsListTint, SET_GRADIENT_DIAGONAL);
                    break;
                case SELECT_TYPE_GRADIENT_PLASMA:
                    applyWallpaper(colorsListTint, SET_GRADIENT_PLASMA);
                    break;
                case SELECT_TYPE_STRIPES:
                    applyWallpaper(colorsListTint, SET_STRIPES);
                    break;
            }
        } else if (view.getId() == R.id.btn_rotate) {
            if (!mTintView.hasRotate()) {
                mTintView.setRotateMode(TintWallView.INVERT);
                mTintView.invalidate();
            } else {
                mTintView.setRotateMode(TintWallView.NORMAL);
                mTintView.invalidate();
            }
            gradientDrawableSTART.setColor(mTintView.getColorOne()); // change color
            mButtonStart.setBackground(layerDrawableSTART);
            setTextColorButton(mButtonStart, mTintView.getColorOne());

            gradientDrawableEND.setColor(mTintView.geColorTwo()); // change color
            mButtonEnd.setBackground(layerDrawableEND);
            setTextColorButton(mButtonEnd, mTintView.geColorTwo());

        } if (((String) view.getTag()) != null && ((String) view.getTag()).equalsIgnoreCase("1")) {
            new ColorPickerDialog.Builder(mContext)
                    .initialColor(mTintView.getColorAtPosition(POSITION_LIST_COLOR_ONE))
                    .colorsPaletteCustom(colorsListPicker)
                    .cancelable(true)
                    .showAlpha(false)
                    .activeAlpha(false)
                    .selectMode(ColorPickerDialog.SelectMode.PALETTE)
                    .backgroundColor(getResources().getColor(R.color.primary_1_dark))
                    .listener(new ColorPickerDialog.OnColorChangedListener() {
                        @Override
                        public void colorChanged(DialogInterface dialog, int selectedColor) {

                            float[] hsv = new float[3];
                            Color.colorToHSV(selectedColor, hsv);

                            mColorsList = null;
                            mColorsList = new ArrayList<>(mSelectColors);

                            switch (mSelectColors) {
                                case SELECT_COLOR_ONE:
                                    mColorsList.add(ColorUtils.colorIntToColorHex(selectedColor, false));
                                    
                                    updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_ONE)).getChildAt(0)), mTintView.getColorAtPosition(POSITION_LIST_COLOR_ONE));
                                    break;
                                case SELECT_COLOR_TWO:
                                    mColorsList.add(ColorUtils.colorIntToColorHex(selectedColor, false));
                                    mColorsList.add(ColorUtils.colorIntToColorHex(mTintView.getColorAtPosition(POSITION_LIST_COLOR_TWO), false));
                                    
                                    updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_ONE)).getChildAt(0)), mTintView.getColorAtPosition(POSITION_LIST_COLOR_ONE));
                                    updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_TWO)).getChildAt(0)), mTintView.getColorAtPosition(POSITION_LIST_COLOR_TWO));
                                    break;
                                case SELECT_COLOR_THREE:
                                    mColorsList.add(ColorUtils.colorIntToColorHex(mTintView.getColorAtPosition(POSITION_LIST_COLOR_ONE), false));
                                    mColorsList.add(ColorUtils.colorIntToColorHex(mTintView.getColorAtPosition(POSITION_LIST_COLOR_TWO), false));
                                    mColorsList.add(ColorUtils.colorIntToColorHex(selectedColor, false));
                                    
                                    updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_ONE)).getChildAt(0)), mTintView.getColorAtPosition(POSITION_LIST_COLOR_ONE));
                                    updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_TWO)).getChildAt(0)), mTintView.getColorAtPosition(POSITION_LIST_COLOR_TWO));
                                    updateButtonPressTint(((Button) ((LinearLayout) mTintButtons.getChildAt(SELECT_COLOR_THREE)).getChildAt(0)), mTintView.getColorAtPosition(POSITION_LIST_COLOR_THREE));
                                    break;
                            }
                            mTintView.setColorsList(mColorsList);
                            mTintView.invalidate();
                        }
                    })
                    .build().show((((AppCompatActivity) getActivity()).getSupportFragmentManager()), null);
        } if (((String) view.getTag()) != null && ((String) view.getTag()).equalsIgnoreCase("2")) {

        } if (((String) view.getTag()) != null && ((String) view.getTag()).equalsIgnoreCase("3")) {

        }
    }

    protected Bitmap generateGradientVertical(ArrayList<String> colorsList) {
        WallpaperManager wpManager = WallpaperManager.getInstance(getContext());
        // Use full screen size so wallpaper is movable.
        int height = wpManager.getDesiredMinimumHeight();
        int width = wpManager.getDesiredMinimumWidth();
        // Create square bitmap for wallpaper.
        Bitmap wallpaperBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // Prepare colors for gradient.
        int[] colorsInt = new int[colorsList.size()];
        for (int i = 0; i < colorsList.size(); i++) {
            colorsInt[i] = Color.parseColor(colorsList.get(i));
        }
        // Create gradient shader.
        Paint paint = new Paint();
        Shader gradientShader = new LinearGradient(0, 0, 0, height, colorsInt, null, Shader.TileMode.CLAMP);
        Canvas c = new Canvas(wallpaperBitmap);
        paint.setShader(gradientShader);
        // Draw gradient on bitmap.
        c.drawRect(0, 0, width, height, paint);
        // Add noise.
        //addNoise(wallpaperBitmap);

        return wallpaperBitmap;
    }

    protected Bitmap generateGradientDiagonal(ArrayList<String> colorsList) {
        WallpaperManager wpManager = WallpaperManager.getInstance(getContext());
        // Use full screen size so wallpaper is movable.
        int height = wpManager.getDesiredMinimumHeight();
        // Create square bitmap for wallpaper.
        Bitmap wallpaperBitmap = Bitmap.createBitmap(height, height, Bitmap.Config.ARGB_8888);
        // Prepare colors for gradient.
        int[] colorsInt = new int[colorsList.size()];
        for (int i = 0; i < colorsList.size(); i++) {
            colorsInt[i] = Color.parseColor(colorsList.get(i));
        }
        // Create gradient shader.
        Paint paint = new Paint();
        Shader gradientShader = new LinearGradient(0, 0, height, height, colorsInt, null, Shader.TileMode.CLAMP);
        Canvas c = new Canvas(wallpaperBitmap);
        paint.setShader(gradientShader);
        // Draw gradient on bitmap.
        c.drawRect(0, 0, height, height, paint);
        // Add noise.
        //addNoise(wallpaperBitmap);

        return wallpaperBitmap;
    }

    protected Bitmap generateGradientPlasma(ArrayList<String> colorsList) {
        WallpaperManager wpManager = WallpaperManager.getInstance(getContext());
        // Use half screen size for speed.
        int height = wpManager.getDesiredMinimumHeight() / 2;
        int width = height;
        // Create wallpaper bitmap.
        Bitmap wallpaperBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // Prepare colors for gradient.
        int[] colorsInt = new int[colorsList.size()];
        for (int i = 0; i < colorsList.size(); i++) {
            colorsInt[i] = Color.parseColor(colorsList.get(i));
        }
        // Create gradient to construct palette.
        Paint paint = new Paint();
        Bitmap gradientBitmap = Bitmap.createBitmap(256, 1, Bitmap.Config.ARGB_8888);
        Shader gradientShader = new LinearGradient(0, 0, 255, 0, colorsInt, null, Shader.TileMode.MIRROR);
        Canvas c = new Canvas(gradientBitmap);
        paint.setShader(gradientShader);
        // Draw gradient on bitmap.
        c.drawRect(0, 0, 256, 1, paint);
        int[] palette = new int[256];
        //
        for (int x = 0; x < 256; x++) {
            palette[x] = gradientBitmap.getPixel(x, 0);
        }
        // Cleanup.
        gradientBitmap.recycle();

        // Generate plasma.
        int[][] plasma = new int[width][height];
        Random random = new Random();
        // TODO: add n (and maybe spread) as parameter in Settings.
        double n = 1.3;  // Number of periods per wallpaper width.
        double period = width / (n * 2 * 3.14);
        double spread = period * 0.3;
        double period1 = period - spread + spread * random.nextFloat();
        double period2 = period - spread + spread * random.nextFloat();
        double period3 = period - spread + spread * random.nextFloat();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                // Adding sines to get plasma value.
                int value = (int)
                        (
                                128.0 + (128.0 * Math.sin(x / period1))
                                        + 128.0 + (128.0 * Math.sin(y / period2))
                                        + 128.0 + (128.0 * Math.sin((x + y) / period1))
                                        + 128.0 + (128.0 * Math.sin(Math.sqrt((double) (x * x + y * y)) / period3))
                        ) / 4;
                plasma[x][y] = value;
            }
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                int color = palette[plasma[x][y] % 256];
                wallpaperBitmap.setPixel(x, y, color);
            }
        // TODO: Add noise as option in Settings.
        //addNoise(wallpaperBitmap);
        wallpaperBitmap = Bitmap.createScaledBitmap(wallpaperBitmap, wpManager.getDesiredMinimumHeight(), wpManager.getDesiredMinimumHeight(), true);

        return wallpaperBitmap;
    }

    protected Bitmap generateStripes(ArrayList<String> colorsList) {
        WallpaperManager wpManager = WallpaperManager.getInstance(getContext());
        // Use full screen size so wallpaper is movable.
        int smallHeight = wpManager.getDesiredMinimumHeight();
        int smallWidth = smallHeight;
        // Big height to account for rotation.
        int bigHeight = 2 * smallHeight;
        int bigWidth = bigHeight;
        // Medium height for color distribution.
        int middleHeight = (int) ((2 * smallHeight) / Math.sqrt(2));
        // Offset for cropping.
        int offset = (bigHeight - middleHeight) / 2;
        // Create square bitmap for wallpaper.
        Bitmap bigBitmap = Bitmap.createBitmap(bigWidth, bigHeight, Bitmap.Config.ARGB_8888);
        // Prepare colors.
        int[] colorsInt = new int[colorsList.size()];
        for (int i = 0; i < colorsList.size(); i++) {
            colorsInt[i] = Color.parseColor(colorsList.get(i));
        }
        Canvas c = new Canvas(bigBitmap);
        // Rotate canvas before drawing.
        c.save();
        c.rotate(-45, c.getWidth() / 2, c.getHeight() / 2);
        Paint paint = new Paint();
        float initStripeHeight = middleHeight / colorsList.size();
        float initShadowHeight = (float) (middleHeight * 0.012);
        int stripeSpread = (int) (initStripeHeight * 0.25);  // Vary stripe height a bit.
        float shadowSpread = initShadowHeight * 0.5f;  // Vary shadow thickness too.
        for (int i = colorsList.size() - 1; i >= 0; i--) {  // Going upwards.
            int stripeHeight;
            float shadowThickness;
            if (i == colorsList.size() - 1) {  // Fill whole canvas with last color.
                stripeHeight = bigHeight;
                shadowThickness = 0;
            } else {
                stripeHeight = Math.round((i + 1) * initStripeHeight);
                int dh = (int) (stripeSpread * Math.random() - stripeSpread / 2);
                stripeHeight = offset + stripeHeight + dh;
                if (stripeHeight < 0) stripeHeight = 0;
                if (stripeHeight > bigHeight) stripeHeight = bigHeight;
                float ds = (float) (shadowSpread * Math.random() - shadowSpread / 2);
                shadowThickness = Math.max(1, initShadowHeight + ds);
            }
            paint.setColor(colorsInt[i]);
            paint.setStyle(Paint.Style.FILL);
            paint.setShadowLayer(shadowThickness, 0.0f, 0.0f, 0x77000000);
            c.drawRect(0, 0, bigWidth, stripeHeight, paint);
        }
        // Rotate canvas back.
        c.restore();
        //  Crop to screen size.
        int x = (c.getWidth() - smallWidth) / 2;
        int y = (c.getHeight() - smallHeight) / 2;
        Bitmap wallpaperBitmap = Bitmap.createBitmap(bigBitmap, x, y, smallWidth, smallHeight);
        // Add noise.
        //addNoise(wallpaperBitmap);

        // Cleanup.
        bigBitmap.recycle();

        return wallpaperBitmap;
    }


    private static final int SET_GRADIENT_VERTICAL = 0;
    private static final int SET_GRADIENT_DIAGONAL = 1;
    private static final int SET_GRADIENT_PLASMA = 2;
    private static final int SET_STRIPES = 3;

    private static class AsyncTaskParams {
        String[] colors;
        int operation;

        AsyncTaskParams(String[] colors, int operation) {
            this.colors = colors;
            this.operation = operation;
        }
    }

    private class SetWallpaperTask extends AsyncTask<AsyncTaskParams, Void, Boolean> {

        private ProgressDialog progressDialog;
        private Context context;
        private boolean mStatesApply[];
        private boolean mFixed;

        private Bitmap mResultBitmap;

        public SetWallpaperTask(Context context, @Nullable boolean[] statesApply, boolean fixed) {
            this.context = context;
            this.mStatesApply = statesApply;
            this.mFixed = fixed;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.applying_wallpaper));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            //progress.cancelable(false);
            //progress.setCanceledOnTouchOutside(false);
            progressDialog.getContext().setTheme(R.style.Dialog_InsWall_ProgressDefault);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(AsyncTaskParams... params) {
            String[] colors = params[0].colors;
            ArrayList<String> colorsList = new ArrayList(Arrays.asList(colors));
            int operation = params[0].operation;
            if (operation == SET_GRADIENT_VERTICAL) {
                try {
                    mResultBitmap = generateGradientVertical(colorsList);
                } catch (OutOfMemoryError | Exception e) {
                    e.printStackTrace();
                }
            }
            if (operation == SET_GRADIENT_DIAGONAL) {
                try {
                    mResultBitmap = generateGradientDiagonal(colorsList);
                } catch (OutOfMemoryError | Exception e) {
                    e.printStackTrace();
                }
            }
            if (operation == SET_GRADIENT_PLASMA) {
                try {
                    mResultBitmap = generateGradientPlasma(colorsList);
                } catch (OutOfMemoryError | Exception e) {
                    e.printStackTrace();
                }
            }
            if (operation == SET_STRIPES) {
                try {
                    mResultBitmap = generateStripes(colorsList);
                } catch (OutOfMemoryError | Exception e) {
                    e.printStackTrace();
                }
            }
            return Utils.setWallaper(((AppCompatActivity) context), mResultBitmap, mStatesApply, mFixed);
        }

        protected void onProgressUpdate() {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                try {
                    WallpaperSuccessDialog.create(context, context.getResources().getString(R.string.success),
                            context.getString(R.string.success_description))
                            .show(((AppCompatActivity) context).getSupportFragmentManager(), "Success");
                } catch (IllegalStateException | IllegalArgumentException e) {
                    //Aprobechamos el error de que no se encontro un contexto para el dialog
                    //cuando nos situamos fuara de su contexto inicial.
                    Utils.showToast(((AppCompatActivity) context), R.string.wallpaper_set, R.color.successful);
                } finally {
                    //mActivity.setResult(Activity.RESULT_OK);
                }
            } else {
                Utils.showToast(((AppCompatActivity) context), R.string.error_apply_wallpaper, R.color.error);
            }

            try {
                if ((this.progressDialog != null) && this.progressDialog.isShowing()) {
                    this.progressDialog.dismiss();
                }
            } catch (final IllegalArgumentException e) {
                // Handle or log or ignore
            } catch (final Exception e) {
                // Handle or log or ignore
            } finally {
                this.progressDialog = null;
            }

            // Cleanup.
            mResultBitmap.recycle();
        }
    }


    @Override
    public boolean onLongClick(View view) {
        final LayerDrawable layerDrawableSTART = (LayerDrawable) ContextCompat.getDrawable(
                getActivity(), R.drawable.circle_image_picker_tint);
        final GradientDrawable gradientDrawableSTART = (GradientDrawable) layerDrawableSTART
                .findDrawableByLayerId(R.id.oval_color);

        final LayerDrawable layerDrawableEND = (LayerDrawable) ContextCompat.getDrawable(
                getActivity(), R.drawable.circle_image_picker_tint);
        final GradientDrawable gradientDrawableEND = (GradientDrawable) layerDrawableEND
                .findDrawableByLayerId(R.id.oval_color);

        switch (view.getId()) {
            case R.id.btnStart:
                Utils.vibrate(getActivity(), 100); //Vibrate
                break;
            case R.id.btnEnd:
                Utils.vibrate(getActivity(), 100); //Vibrate
                break;
            case R.id.btn_randomize:
                Utils.showToolTip(view, getActivity(), String.valueOf(view.getContentDescription()));
                break;
            case R.id.btn_apply:
                Utils.showToolTip(view, getActivity(), String.valueOf(view.getContentDescription()));
                break;
            case R.id.btn_rotate:
                Utils.showToolTip(view, getActivity(), String.valueOf(view.getContentDescription()));
                break;
        }
        return false;
    }


    private static final int APPLY_TYPE_HOME = 0;
    private static final int APPLY_TYPE_LOCK = 1;
    private static final int APPLY_TYPE_BOTH = 2;

    private void applyWallpaper(final Bitmap bitmap) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            boolean success = false;
            boolean statesApply[] = null;
            boolean fixed = false;

            mTintView.invalidate();
            success = Utils.setWallaper(getActivity(), bitmap, statesApply, fixed);
            mTintView.setDrawingCacheEnabled(false); // clear drawing cache
            if (success) {
                //Utils.showToast(getActivity(), R.string.wallpaper_set, R.color.successful);
                try {
                    WallpaperSuccessDialog.create(getActivity(), getResources().getString(R.string.success),
                            getResources().getString(R.string.success_description)).show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "Success");
                } catch (IllegalStateException | IllegalArgumentException e) {
                    e.printStackTrace();
                    //Aprobechamos el error de que no se encontro un contexto para el dialog
                    //cuando nos situamos fuara de su contexto inicial.
                    Utils.showToast(getActivity(), R.string.wallpaper_set, R.color.successful);
                }
            } else {
                Utils.showToast(getActivity(), R.string.error_apply_wallpaper, R.color.error);
            }
        } else {
            new DefaultDialog.Builder(getActivity())
                    .dividerColorRes(R.color.divider_dark)
                    .titleGravity(DefaultDialog.GravityType.START)
                    .contentGravity(DefaultDialog.GravityType.START)
                    .cancelable(true)
                    .dialogType(DefaultDialog.DialogType.ITEMS)
                    .inflateMenu(R.menu.menu_item_apply_wallpaper)
                    .onItemsListener(DefaultDialog.VALUE_NULL, new DefaultDialog.OnItemsListener() {
                        @Override
                        public void onSelection(DialogInterface dialog, DefaultDialog.Item item, int position) {
                            switch (item.getId()) {
                                case R.id.menu_apply_wallpaper_home:
                                    onOptionApply(APPLY_TYPE_HOME, bitmap);
                                    break;
                                case R.id.menu_apply_wallpaper_lock:
                                    onOptionApply(APPLY_TYPE_LOCK, bitmap);
                                    break;
                                case R.id.menu_apply_wallpaper_both:
                                    onOptionApply(APPLY_TYPE_BOTH, bitmap);
                                    break;
                            }
                            dialog.dismiss();
                        }
                    })
                    .build().show((((AppCompatActivity) getActivity()).getSupportFragmentManager()), null);
        }
    }

    private void onOptionApply(int typeApply, Bitmap bitmap) {
        boolean whichSystem = true, whichLock = true;
        if (typeApply == APPLY_TYPE_HOME) {
            whichSystem = true;
            whichLock = false;
        } else if (typeApply == APPLY_TYPE_LOCK) {
            whichSystem = false;
            whichLock = true;
        } else if (typeApply == APPLY_TYPE_BOTH) {
            whichSystem = true;
            whichLock = true;
        }

        boolean statesApply[] = new boolean[]{whichSystem, whichLock};
        boolean fixed = true;
        boolean success = false;
        mTintView.invalidate();
        success = Utils.setWallaper(getActivity(), bitmap, statesApply, fixed);
        if (success) {
            //Utils.showToast(getActivity(), R.string.wallpaper_set, R.color.successful);
            try {
                WallpaperSuccessDialog.create(getActivity(), getResources().getString(R.string.success),
                        getResources().getString(R.string.success_description))
                        .show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "Success");
            } catch (IllegalStateException | IllegalArgumentException e) {
                e.printStackTrace();
                //Aprobechamos el error de que no se encontro un contexto para el dialog
                //cuando nos situamos fuara de su contexto inicial.
                Utils.showToast(getActivity(), R.string.wallpaper_set, R.color.successful);
            }
        } else {
            Utils.showToast(getActivity(), R.string.error_apply_wallpaper, R.color.error);
        }
    }


    private void applyWallpaper(final ArrayList<String> colorsList, final int operation) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            boolean statesApply[] = null;
            boolean fixed = false;

            AsyncTaskParams params;
            SetWallpaperTask asyncTask;

            String[] selectedArray = colorsList.toArray(new String[colorsList.size()]);

            params = new AsyncTaskParams(selectedArray, operation);
            asyncTask = new SetWallpaperTask(getActivity(), statesApply, fixed);
            asyncTask.execute(params);
        } else {
            new DefaultDialog.Builder(getActivity())
                    .dividerColorRes(R.color.divider_dark)
                    .cancelable(true)
                    .dialogType(DefaultDialog.DialogType.ITEMS)
                    .inflateMenu(R.menu.menu_item_apply_wallpaper)
                    .onItemsListener(DefaultDialog.VALUE_NULL, new DefaultDialog.OnItemsListener() {
                        @Override
                        public void onSelection(DialogInterface dialog, DefaultDialog.Item item, int position) {
                            switch (item.getId()) {
                                case R.id.menu_apply_wallpaper_home:
                                    onOptionApply(APPLY_TYPE_HOME, colorsList, operation);
                                    break;
                                case R.id.menu_apply_wallpaper_lock:
                                    onOptionApply(APPLY_TYPE_LOCK, colorsList, operation);
                                    break;
                                case R.id.menu_apply_wallpaper_both:
                                    onOptionApply(APPLY_TYPE_BOTH, colorsList, operation);
                                    break;
                            }
                            dialog.dismiss();
                        }
                    })
                    .build().show((((AppCompatActivity) getActivity()).getSupportFragmentManager()), null);
        }
    }

    private void onOptionApply(int typeApply, ArrayList<String> colorsList, int operation) {
        boolean whichSystem = true, whichLock = true;
        if (typeApply == APPLY_TYPE_HOME) {
            whichSystem = true;
            whichLock = false;
        } else if (typeApply == APPLY_TYPE_LOCK) {
            whichSystem = false;
            whichLock = true;
        } else if (typeApply == APPLY_TYPE_BOTH) {
            whichSystem = true;
            whichLock = true;
        }

        boolean statesApply[] = new boolean[]{whichSystem, whichLock};
        boolean fixed = false;

        AsyncTaskParams params;
        SetWallpaperTask asyncTask;

        String[] selectedArray = colorsList.toArray(new String[colorsList.size()]);

        params = new AsyncTaskParams(selectedArray, operation);
        asyncTask = new SetWallpaperTask(getActivity(), statesApply, fixed);
        asyncTask.execute(params);
    }
}
