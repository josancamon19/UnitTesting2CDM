package com.josancamon19.unittesting2cdm.ui.noteslist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.josancamon19.unittesting2cdm.R;
import com.josancamon19.unittesting2cdm.models.Note;
import com.josancamon19.unittesting2cdm.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = "NotesRecyclerAdapter";

    private List<Note> notes = new ArrayList<>();
    private OnNoteListener onNoteListener;

    public NotesRecyclerAdapter(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            String month = notes.get(position).getTimestamp().substring(0, 2);
            month = DateUtil.getMonthFromNumber(month);
            String year = notes.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            ((ViewHolder) holder).timestamp.setText(timestamp);
            ((ViewHolder) holder).title.setText(notes.get(position).getTitle());
        } catch (NullPointerException e) {
            Timber.e("onBindViewHolder: Null Pointer: %s", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getNote(int position) {
        if (notes.size() > 0) {
            return notes.get(position);
        }
        return null;
    }

    public void removeNote(Note note) {
        notes.remove(note);
        notifyDataSetChanged();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timestamp, title;
        OnNoteListener mOnNoteListener;

        public ViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            title = itemView.findViewById(R.id.note_title);
            mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Timber.d("onClick: %s", getAdapterPosition());
            mOnNoteListener.onNoteClick(getNote(getAdapterPosition()));
        }
    }

    public interface OnNoteListener {
        void onNoteClick(Note note);
    }
}