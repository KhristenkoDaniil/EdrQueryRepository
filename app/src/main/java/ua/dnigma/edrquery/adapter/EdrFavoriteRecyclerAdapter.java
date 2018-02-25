package ua.dnigma.edrquery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.dnigma.edrquery.R;
import ua.dnigma.edrquery.callback.OnCheckboxCallback;
import ua.dnigma.edrquery.callback.OnViewDeclarationCallback;
import ua.dnigma.edrquery.model.Item;

/**
 * Created by Даниил on 26.11.2017.
 */

public class EdrFavoriteRecyclerAdapter extends RecyclerView.Adapter<EdrFavoriteRecyclerAdapter.EdrHolder> {

    private LayoutInflater inflater;
    private List<Item> edrItems = new ArrayList<>();
    OnCheckboxCallback onCheckboxCallback;
    OnViewDeclarationCallback onViewDeclarationCallback;


    public EdrFavoriteRecyclerAdapter(Context context, List<Item> items, OnCheckboxCallback onCheckboxCallback,
                                      OnViewDeclarationCallback onViewDeclarationCallback) {
        this.inflater = LayoutInflater.from(context);
        this.edrItems = items;
        this.onCheckboxCallback = onCheckboxCallback;
        this.onViewDeclarationCallback = onViewDeclarationCallback;
    }

    @Override
    public EdrHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.edr_favorite_item, parent, false);
        EdrHolder edrHolder = new EdrHolder(view);
        return edrHolder;
    }

    @Override
    public void onBindViewHolder(final EdrHolder holder, int position) {
        final Item edrItem = edrItems.get(position);

        holder.lastName.setText(edrItem.getLastname());
        holder.firstName.setText(edrItem.getFirstname());
        holder.workPlace.setText(edrItem.getPlaceOfWork());
        holder.position.setText(edrItem.getPosition());
        holder.comment.setText(edrItem.getComents());

        holder.checkBoxGoldStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    onCheckboxCallback.onChecked(edrItem);
                } else {
                    onCheckboxCallback.unChecked(edrItem.getId());
                }

            }
        });

        if (edrItem.getFavorite()) {
            holder.checkBoxGoldStar.setChecked(true);
        } else {
            holder.checkBoxGoldStar.setChecked(false);
        }

        holder.bookViewDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewDeclarationCallback.onSucssesWebviewDeclaration(edrItem.getLinkPDF());

            }
        });


    }

    @Override
    public int getItemCount() {
        return edrItems.size();
    }

    public class EdrHolder extends RecyclerView.ViewHolder {

        private TextView lastName;
        private TextView firstName;
        private TextView workPlace;
        private TextView position;
        private EditText comment;
        private CheckBox checkBoxGoldStar;
        private ImageView bookViewDeclaration;


        public EdrHolder(View itemView) {
            super(itemView);
            lastName = (TextView) itemView.findViewById(R.id.last_name);
            firstName = (TextView) itemView.findViewById(R.id.first_name);
            workPlace = (TextView) itemView.findViewById(R.id.work_place);
            comment = (EditText) itemView.findViewById(R.id.comment);
            position = itemView.findViewById(R.id.position);
            checkBoxGoldStar = itemView.findViewById(R.id.checkbox_gold_star);
            bookViewDeclaration = itemView.findViewById(R.id.book_view_declaration);
        }
    }
}
