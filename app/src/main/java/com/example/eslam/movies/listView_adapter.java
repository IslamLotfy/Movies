//package com.example.eslam.movies;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
///**
// * Created by Eslam on 9/16/2016.
// */
//public class listView_adapter extends ArrayAdapter<review> {
//    review[] reviews;
//    listView_adapter(Context context,review[] reviews){
//        super(context,0,reviews);
//        this.reviews=reviews;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.review_list_item, parent, false);
//        }
//        TextView author=(TextView)convertView.findViewById(R.id.author);
//        TextView content=(TextView)convertView.findViewById(R.id.content);
//        author.setText(reviews[position].getAuthor());
//        content.setText(reviews[position].getContent());
//        return convertView;
//    }
//
//}
