package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codedleaf.sylveryte.attendanceapp.R;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FragmentManager mFM;

    @Override
    protected void onResume() {
        super.onResume();
        if (mSectionsPagerAdapter != null) {
            mSectionsPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set db
        DatabaseLab.getInstance(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mFM=getSupportFragmentManager();
        mSectionsPagerAdapter = new SectionsPagerAdapter(mFM);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mSectionsPagerAdapter != null) {
                    mSectionsPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                mSectionsPagerAdapter.notifyDataSetChanged();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case  R.id.parcentance:
            {
                Intent intent=new Intent(MainActivity.this,parcentanceeeeeeeeeee.class);
                startActivity(intent);
                break;

            }

            case  R.id.aboutt:
            {
                Intent intent=new Intent(MainActivity.this,About.class);
                startActivity(intent);
                break;

            }

            case R.id.add_klass :
            {
                startActivityForResult(AdditionActivity.fetchIntent(MainActivity.this, AdditionActivity.ADDKLASS),0);
                break;
            }
            case R.id.add_lecture :
            {
                startActivityForResult(AdditionActivity.fetchIntent(MainActivity.this, AdditionActivity.ADDLECTURE),0);
                break;
            }
            case R.id.add_attendance :
            {
                FragmentManager fragmentManager=getSupportFragmentManager();
                ListDialog dialog=new ListDialog();
                dialog.show(fragmentManager,"LISTDIALOG");
            }
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0: return AttendanceFragment.newInstance();
                case 1: return LectureFragment.newInstance();
                case 2 : return KlassFragment.newInstance();
                default: return null;
            }
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Attendance";
                case 1:
                    return "Lecture";
                case 2:
                    return "Class";
            }
            return null;
        }
    }


}
