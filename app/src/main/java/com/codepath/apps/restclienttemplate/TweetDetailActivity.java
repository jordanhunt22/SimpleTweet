package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.TimeFormatter.TimeFormatter;
import com.codepath.apps.restclienttemplate.databinding.ActivityTweetDetailBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailActivity extends AppCompatActivity {

    // The tweet to display
    Tweet tweet;

    // The view objects
    ImageView ivProfileImage;
    TextView tvBody;
    TextView tvName;
    TextView tvScreenName;
    TextView tvTime;
    ImageView ivMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implement  ViewBinding
        ActivityTweetDetailBinding binding = ActivityTweetDetailBinding.inflate(getLayoutInflater());
        View main = binding.getRoot();
        setContentView(main);

        // Resolve the view objects
        ivProfileImage = binding.ivProfileImage;
        tvBody = binding.tvBody;
        tvName = binding.tvName;
        tvScreenName = binding.tvScreenName;
        tvTime = binding.tvTime;
        ivMedia =binding.ivMedia;

        // Unwrap the movie passed via the intent
        tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        // Set TextViews
        tvBody.setText(tweet.getBody());
        tvName.setText(tweet.getUser().name);
        tvTime.setText(TimeFormatter.getTimeStamp(tweet.createdAt));
        tvScreenName.setText("@" + tweet.getUser().screenName);

        // Set ImageViews
        // Set radius for glide
        int radius = 30;
        Glide.with(this).load(tweet.getUser().profileImageUrl).transform(new RoundedCorners(radius)).into(ivProfileImage);

        if (tweet.DISPLAY_URL != null){
            radius = 50;
            Glide.with(this).load(tweet.DISPLAY_URL).transform(new RoundedCorners(radius)).into(ivMedia);
        }
        else {
            ivMedia.setVisibility(View.GONE);
        }
    }
}