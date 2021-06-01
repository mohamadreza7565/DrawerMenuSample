package com.mra.drawer_menu_samplde.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mra.drawer_menu_samplde.R;

import java.util.ArrayList;
import java.util.List;

public class NavDrawerRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private List<DrawerItem> list;
    public static final int ACTIVITY_TYPE = 1, CALL_TYPE = 2, SHARE_TYPE = 3;

    public NavDrawerRVAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();

        list.add(new DrawerItem(R.drawable.ic_call, CALL_TYPE, "تماس با ما", false, "09111111111"));
        list.add(new DrawerItem(R.drawable.ic_help, ACTIVITY_TYPE, "سوالات متداول", false, "com.mra.drawer_menu_samplde.QuestionsActivity"));
        list.add(new DrawerItem(R.drawable.ic_info, ACTIVITY_TYPE, "درباره ما", false, "com.mra.drawer_menu_samplde.AboutUsActivity"));
        list.add(new DrawerItem(R.drawable.ic_share, SHARE_TYPE, "اشتراک گذاری", false, "متن اشتراک گزاری"));
        list.add(new DrawerItem(R.drawable.ic_unbookmark, ACTIVITY_TYPE, "نشانه گذاری شده ها", false, "com.mra.drawer_menu_samplde.BookmarkActivity"));

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.layout_rv_drawer_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        DrawerItem drawerItem = list.get(position);

        viewHolder.tv_title.setText(drawerItem.getTitle());
        viewHolder.imv_icon.setImageResource(drawerItem.getIcon());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView imv_icon;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            imv_icon = itemView.findViewById(R.id.imv_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, DrawerItem item);
    }

    public NavDrawerRVAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public class DrawerItem {

        int icon, type;
        String title, output;
        boolean dividerVisibility;

        public DrawerItem() {
        }

        public DrawerItem(int icon, int type, String title, boolean dividerVisibility, String output) {
            this.icon = icon;
            this.type = type;
            this.title = title;
            this.output = output;
            this.dividerVisibility = dividerVisibility;
        }

        public int getType() {
            return type;
        }

        public DrawerItem setType(int type) {
            this.type = type;
            return this;
        }

        public String getOutput() {
            return output;
        }

        public DrawerItem setOutput(String output) {
            this.output = output;
            return this;
        }

        public int getIcon() {
            return icon;
        }

        public DrawerItem setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public DrawerItem setTitle(String title) {
            this.title = title;
            return this;
        }

        public boolean isDividerVisibility() {
            return dividerVisibility;
        }

        public DrawerItem setDividerVisibility(boolean dividerVisibility) {
            this.dividerVisibility = dividerVisibility;
            return this;
        }
    }

}
