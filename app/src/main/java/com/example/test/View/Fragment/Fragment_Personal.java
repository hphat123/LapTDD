package com.example.test.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.test.Condition.Contain;
import com.example.test.Database.DataBase;
import com.example.test.Preferences.ManagerPreferences;
import com.example.test.R;

public class Fragment_Personal extends Fragment implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private View mView;

    private RelativeLayout mLayoutTitleNews;

    private RelativeLayout mLayoutTitleVideos;

    private RelativeLayout mLayoutTitleSystems;

    private RelativeLayout mLayoutTitleApp;

    private LinearLayout mLayoutContainNews;

    private LinearLayout mLayoutContainVideos;

    private LinearLayout mLayoutContainSystems;

    private LinearLayout mLayoutContainApp;

    private LinearLayout mLayoutReport;

    private ImageView mImgNews;

    private ImageView mImgVideo;

    private ImageView mImgSystems;

    private ImageView mImgApp;

    private SeekBar mSeekTextSize;

    private ManagerPreferences mManagerPreferences;

    private SwitchCompat mSwitchNotImage;

    private SwitchCompat mSwitchLoadAutoImage;

    private RelativeLayout mLayoutMarkNews;

    private RelativeLayout mLayoutHistoryNews;

    private RelativeLayout mLayoutReadOffline;

    private RelativeLayout mLayoutVideoLove;

    private RelativeLayout mLayoutHistoryVideo;

    private ImageView mImgDeleMark;

    private ImageView mImgDelHistoryNews;

    private ImageView mImgDelReadOff;

    private ImageView mImgDelVideoLove;

    private ImageView mImgDelHistoryVideo;

    private SQLiteDatabase mDb;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mManagerPreferences = new ManagerPreferences(getContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_personal, container, false);

        mDb = DataBase.initDatabase(getContext(), Contain.DATABASE_NAME);

        initView(mView);

        mSeekTextSize.setProgress(mManagerPreferences.getTextSizeWebView());

        mSwitchNotImage.setChecked(mManagerPreferences.getSettingNotImage());

        mSwitchLoadAutoImage.setChecked(mManagerPreferences.getSettingAutoLoadImage());

        return mView;
    }

    private void initView(final View view) {

        mLayoutTitleNews = view.findViewById(R.id.layout_title_news);
        mLayoutTitleNews.setOnClickListener(this);

        mLayoutTitleVideos = view.findViewById(R.id.layout_title_video);
        mLayoutTitleVideos.setOnClickListener(this);

        mLayoutTitleSystems = view.findViewById(R.id.layout_title_systems);
        mLayoutTitleSystems.setOnClickListener(this);

        mLayoutTitleApp = view.findViewById(R.id.layout_title_app);
        mLayoutTitleApp.setOnClickListener(this);

        mLayoutContainNews = view.findViewById(R.id.layout_contain_news);

        mLayoutContainVideos = view.findViewById(R.id.layout_contain_videos);

        mLayoutContainSystems = view.findViewById(R.id.layout_contain_systems);

        mLayoutContainApp = view.findViewById(R.id.layout_contain_app);

        mImgNews = view.findViewById(R.id.img_title_news);

        mImgVideo = view.findViewById(R.id.img_title_video);

        mImgSystems = view.findViewById(R.id.img_title_systems);

        mImgApp = view.findViewById(R.id.img_title_app);

        mSeekTextSize = view.findViewById(R.id.seekBar_setting_text_size);
        mSeekTextSize.setOnSeekBarChangeListener(this);

        mLayoutReport = view.findViewById(R.id.layout_report);
        mLayoutReport.setOnClickListener(this);

        mSwitchLoadAutoImage = view.findViewById(R.id.sw_load_auto_image);
        mSwitchLoadAutoImage.setOnCheckedChangeListener(this);

        mSwitchNotImage = view.findViewById(R.id.sw_setting_not_image);
        mSwitchNotImage.setOnCheckedChangeListener(this);

        mLayoutMarkNews = view.findViewById(R.id.layout_mark_news);
        mLayoutMarkNews.setOnClickListener(this);

        mLayoutHistoryNews = view.findViewById(R.id.layout_history_news);
        mLayoutHistoryNews.setOnClickListener(this);

        mLayoutReadOffline = view.findViewById(R.id.layout_offline_news);
        mLayoutReadOffline.setOnClickListener(this);

        mLayoutVideoLove = view.findViewById(R.id.layout_video_love);
        mLayoutVideoLove.setOnClickListener(this);

        mLayoutHistoryVideo = view.findViewById(R.id.layout_history_video);
        mLayoutHistoryVideo.setOnClickListener(this);

        mImgDeleMark = view.findViewById(R.id.ic_more_vert_black_24dp_mark);
        mImgDeleMark.setOnClickListener(this);

        mImgDelHistoryNews = view.findViewById(R.id.ic_more_vert_black_24dp_history_news);
        mImgDelHistoryNews.setOnClickListener(this);

        mImgDelReadOff = view.findViewById(R.id.ic_more_vert_black_24dp_read_off);
        mImgDelReadOff.setOnClickListener(this);

        mImgDelVideoLove = view.findViewById(R.id.ic_more_vert_black_24dp_video_love);
        mImgDelVideoLove.setOnClickListener(this);

        mImgDelHistoryVideo = view.findViewById(R.id.ic_more_vert_black_24dp_video_history);
        mImgDelHistoryVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_title_news:

                mImgNews.setImageResource((mLayoutContainNews.getVisibility() == View.VISIBLE ?
                        R.drawable.ic_baseline_arrow_drop_down_24 : R.drawable.ic_baseline_arrow_drop_up_24));

                mLayoutContainNews.setVisibility(mLayoutContainNews.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE);

                break;
            case R.id.layout_title_video:

                mImgVideo.setImageResource((mLayoutContainVideos.getVisibility() == View.VISIBLE ?
                        R.drawable.ic_baseline_arrow_drop_down_24 : R.drawable.ic_baseline_arrow_drop_up_24));

                mLayoutContainVideos.setVisibility(mLayoutContainVideos.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE);
                break;
            case R.id.layout_title_systems:

                mImgSystems.setImageResource((mLayoutContainSystems.getVisibility() == View.VISIBLE ?
                        R.drawable.ic_baseline_arrow_drop_down_24 : R.drawable.ic_baseline_arrow_drop_up_24));

                mLayoutContainSystems.setVisibility(mLayoutContainSystems.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE);
                break;
            case R.id.layout_title_app:

                mImgApp.setImageResource((mLayoutContainApp.getVisibility() == View.VISIBLE ?
                        R.drawable.ic_baseline_arrow_drop_down_24 : R.drawable.ic_baseline_arrow_drop_up_24));

                mLayoutContainApp.setVisibility(mLayoutContainApp.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE);

                break;

            case R.id.layout_report:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", Contain.EMAIL_ADMIN, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[Góp ý & Sửa lỗi]");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;

            case R.id.ic_more_vert_black_24dp_mark:
                showPopupMenu(mImgDeleMark, 0);
                break;
            case R.id.ic_more_vert_black_24dp_history_news:
                showPopupMenu(mImgDelHistoryNews, 1);
                break;
            case R.id.ic_more_vert_black_24dp_read_off:
                showPopupMenu(mImgDelReadOff, 2);
                break;
            case R.id.ic_more_vert_black_24dp_video_love:
                showPopupMenu(mImgDelVideoLove, 3);
                break;
            case R.id.ic_more_vert_black_24dp_video_history:
                showPopupMenu(mImgDelHistoryVideo, 4);
                break;
        }
    }

    /**
     * @param view
     * @param flag
     */
    private void showPopupMenu(final View view, final int flag) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_delete, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_content:
                        switch (flag) {
                            case 0:
                                mDb.execSQL("Delete From tbl_news");
                                Toast.makeText(getContext(), "Thành công!", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                mDb.execSQL("Delete From tbl_history_news");
                                Toast.makeText(getContext(), "Thành công!", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                mDb.execSQL("Delete From tbl_news_offline");
                                Toast.makeText(getContext(), "Thành công!", Toast.LENGTH_SHORT).show();
                                mDb.execSQL("Update tbl_categories_offline set time='Chưa tải dữ liệu'");
                                break;
                            case 3:
                                mDb.execSQL("Delete From tbl_video");
                                Toast.makeText(getContext(), "Thành công!", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                mDb.execSQL("Delete From tbl_history_video");
                                Toast.makeText(getContext(), "Thành công!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mManagerPreferences.setTextSizeWebView(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sw_load_auto_image:
                mManagerPreferences.setSettingAutoLoadImage(isChecked);
                break;
            case R.id.sw_setting_not_image:
                mManagerPreferences.setSettingNotImage(isChecked);
                break;
        }
    }
}
