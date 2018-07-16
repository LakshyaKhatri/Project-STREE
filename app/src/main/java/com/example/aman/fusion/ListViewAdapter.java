package com.example.aman.fusion;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

import static android.view.View.GONE;

/**
 * Created by Lakshya on 27-03-2018.
 */

public class ListViewAdapter extends ArrayAdapter<ListDataItem> {

    public TextToSpeech ttsObject;
    private Context context;
    private ArrayList<ListDataItem> listDataItems;

    public ListViewAdapter(Context context, ArrayList<ListDataItem> listDataItems){
        super(context, 0, listDataItems);
        this.context = context;
        this.listDataItems = listDataItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newListItem = convertView;

        ttsObject = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR)
                    ttsObject.setLanguage(Locale.UK);
            }
        });

        if (convertView == null)
            newListItem = LayoutInflater.from(context).inflate(R.layout.list_items_layout, parent, false);

        final ListDataItem currentItem = listDataItems.get(position);
        ImageView imageView = newListItem.findViewById(R.id.list_item_image);
        if (currentItem.hasImage()) {
            imageView.setVisibility(View.VISIBLE);

            Glide.with(imageView.getContext())
                    .load(currentItem.getImageUrl())
                    .into(imageView);
       }
        else
            imageView.setVisibility(View.GONE);

        final TextView titleTextView = newListItem.findViewById(R.id.list_item_title_text_view);
        titleTextView.setText(currentItem.getTitleText());
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsObject.speak(titleTextView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        final TextView contentTextView = newListItem.findViewById(R.id.list_item_content_text_view);
        if (currentItem.hasContentText()){
            contentTextView.setVisibility(View.VISIBLE);
            contentTextView.setText(currentItem.getContentText());
            contentTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ttsObject.speak(contentTextView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }
        else
            contentTextView.setVisibility(View.GONE);

        TextView extraLinkTextView = newListItem.findViewById(R.id.list_item_link_text_view);
        if(currentItem.hasExtraLink()) {
            extraLinkTextView.setVisibility(View.VISIBLE);
            final String link = currentItem.getExtraLink();
            SpannableString underLinedLink = new SpannableString(link);
            //set the underline:
            underLinedLink.setSpan(new UnderlineSpan(), 0, link.length(), 0);
            extraLinkTextView.setText(underLinedLink);
            extraLinkTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, link); // query contains search string
                    getContext().startActivity(intent);
                }
            });
        }

        else
            extraLinkTextView.setVisibility(View.GONE);

        return newListItem;
    }
}
