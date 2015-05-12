package com.builtio.builtquerylistview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raweng.built.BuiltError;
import com.raweng.built.BuiltObject;
import com.raweng.built.userInterface.BuiltListViewResultCallBack;
import com.raweng.built.userInterface.BuiltUIListViewController;

/**
 * This is built.io android tutorial.
 * 
 * Short introduction of some classes with some methods.
 * Contain classes: 
 * 1. BuiltUIListViewController 
 * 2. BuiltQuery
 * 
 * For quick start with built.io refer "http://docs.built.io/quickstart/index.html#android"
 * 
 * @author raw engineering, Inc
 *
 */
public class PlacesActivity extends Activity {

	/*
	 * Declaration of BuiltUIListViewController.
	 */
	BuiltUIListViewController listview;
    ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * Initialization of BuiltUIListViewController object.
		 */
		listview = new BuiltUIListViewController(PlacesActivity.this, "blt9f2f3c1d77c907e0","places");

        /*
         * Initialization of Progress Dialog and progress Dialog
         */

        initProgressDialog();

        /*
         * set progress Dialog to the BuiltUIListViewController
         */
        listview.setProgressDialog(progressDialog);

		/*
		 * Setting the BuiltUIListViewController layout to activity (Initialization of layout to activity).
		 */
		setContentView(listview.getLayout());

		/*
		 * fetching and loading BuiltObject in list.
		 */
		fetchAllMeal_UsingBuiltQuery();
	}

    private void initProgressDialog() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void fetchAllMeal_UsingBuiltQuery() {

		/*
		 * Setting batch loading of BuiltObject 
		 */
		listview.setLimit(10);
		
		/*
		 * Querying to loading list.
		 */
		listview.getBuiltQueryInstance().includeOwner().descending("updated_at");
		
		/*
		 *  Calling load data 
		 */
		listview.loadData(new BuiltListViewResultCallBack() {

			@Override
			public void onError(BuiltError builtErrorObject) {
				
				/// builtErrorObject contains more details of error.
				Toast.makeText(PlacesActivity.this, "Error : "+builtErrorObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onAlways() {
				
				/// write code here that user want to execute.
				/// regardless of success or failure of the operation.
			}

			@Override
			public int getViewTypeCount() {
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent, BuiltObject builtObject) {

				FavouritePlaceViewHolder viewHolder = null;
				
				///Inflating the row layout in list view.
				if(convertView == null){
					
					LayoutInflater inflater     = LayoutInflater.from(PlacesActivity.this);
					convertView                 = inflater.inflate(R.layout.activity_places, parent, false);
					viewHolder				    = new FavouritePlaceViewHolder();
					
					viewHolder.placeNameTextView = (TextView) convertView.findViewById(R.id.place);
					viewHolder.ratingTextView	 = (TextView) convertView.findViewById(R.id.rating);
					viewHolder.likeTextView	 = (TextView) convertView.findViewById(R.id.likeCount);
					
				}else{
					viewHolder = (FavouritePlaceViewHolder) convertView.getTag();
				}

				if (builtObject != null && viewHolder != null) {
					viewHolder.populateFrom(builtObject);
				}

				return convertView;
			}

			@Override
			public int getItemViewType(int position) {
				return 0;
			}
		});
	}

}
