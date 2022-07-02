package hadi.greencode.instapagedownloader.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hadi.greencode.instapagedownloader.R;
import hadi.greencode.instapagedownloader.models.Post;

public class ListMediaAdapter extends RecyclerView.Adapter<ListMediaAdapter.Holder> {

    private final Context           context;
    private       List<Post>        list;
    private       OnAdapterListener onAdapterListener;

    public interface OnAdapterListener {
        void onItemClicked(int position, Post post);
    }

    public ListMediaAdapter(Context context, List<Post> list, OnAdapterListener onAdapterListener) {
        this.context           = context;
        this.list              = list;
        this.onAdapterListener = onAdapterListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.recycler_media, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Post post = list.get(position);

        Glide.with(context).load(post.getImage()).into(holder.ivPostImage);

        holder.cbImage.setChecked(post.isChecked());

        Drawable image = post.getVideo() ? context.getResources().getDrawable(R.drawable.ic_play_arrow_white_30dp) :
                context.getResources().getDrawable(R.drawable.ic_baseline_image_24);
        Glide.with(context).load(image).into(holder.ivIsVideo);

        holder.parent.setOnClickListener(v -> {
            onAdapterListener.onItemClicked(position, post);

            post.setChecked(!post.isChecked());
            holder.cbImage.setChecked(!post.isChecked());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPostImage)
        AppCompatImageView ivPostImage;

        @BindView(R.id.ivIsVideo)
        AppCompatImageView ivIsVideo;

        @BindView(R.id.cbImage)
        CheckBox cbImage;

        @BindView(R.id.parent)
        ConstraintLayout parent;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
