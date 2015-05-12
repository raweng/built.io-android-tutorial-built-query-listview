package com.builtio.builtquerylistview;

import android.graphics.Color;
import android.widget.TextView;

import com.raweng.built.BuiltObject;

/**
 * This is built.io android tutorial.
 * 
 * Short introduction of some classes with some methods.
 * Contain classes: 
 * 1. BuiltUILoginController 
 * 2. BuiltUISignUpController 
 * 3. BuiltUser
 * 
 * For quick start with built.io refer "http://docs.built.io/quickstart/index.html#android"
 * 
 * @author raw engineering, Inc
 *
 */
public class FavouritePlaceViewHolder {

	public TextView placeNameTextView;
	public TextView ratingTextView;
	public TextView likeTextView;

	public void populateFrom(BuiltObject builtObject) {
		
		/*
		 * Extracting the data from builtObject instance.
		 */
		placeNameTextView.setText(builtObject.getString("place_name"));

		likeTextView.setText("Like : " + builtObject.getInt("likes"));

		Object rating = (Object) builtObject.get("ratings");
		
		if(rating instanceof Integer){
			
			ratingTextView.setText(rating.toString()+".0");
			backgroundForRating(builtObject.getInt("ratings"));
			
		}else if(rating instanceof Double){
			
			ratingTextView.setText(rating.toString());
			Double rate = (Double)rating;
			backgroundForRating(rate.intValue());
		}
	}

	private void backgroundForRating(int rating) {

		switch (rating) {
		case 0:
			ratingTextView.setBackgroundColor(Color.parseColor("#F53A0F"));
			break;
		case 1:
			ratingTextView.setBackgroundColor(Color.parseColor("#F44F0F"));
			break;
		case 2:
			ratingTextView.setBackgroundColor(Color.parseColor("#F4700E"));
			break;
		case 3:
			ratingTextView.setBackgroundColor(Color.parseColor("#F5950D"));
			break;
		case 4:
			ratingTextView.setBackgroundColor(Color.parseColor("#EBB50B"));
			break;

		case 5:
			ratingTextView.setBackgroundColor(Color.parseColor("#E1DE1F"));
			break;
		case 6:
			ratingTextView.setBackgroundColor(Color.parseColor("#B3BB27"));
			break;
		case 7:
			ratingTextView.setBackgroundColor(Color.parseColor("#69BF14"));;
			break;
		case 8:
			ratingTextView.setBackgroundColor(Color.parseColor("#208E13"));
			break;

		case 9:
			ratingTextView.setBackgroundColor(Color.parseColor("#148e26"));
			break;

		default:
			ratingTextView.setBackgroundColor(Color.parseColor("#F53A0F"));
			break;
		}

	}

}

