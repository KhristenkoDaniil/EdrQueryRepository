package ua.dnigma.edrquery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.dnigma.edrquery.R;
import ua.dnigma.edrquery.data.DBHelper;
import ua.dnigma.edrquery.data.EdrInterestTableUpdate;
import ua.dnigma.edrquery.model.Item;

/**
 * Created by Даниил on 26.11.2017.
 */

public class EdrRecyclerAdapter extends RecyclerView.Adapter<EdrRecyclerAdapter.EdrHolder>{

    private LayoutInflater inflater;
    private List<Item> edrItems = new ArrayList<>();
    DBHelper dbHelper;

    public EdrRecyclerAdapter(Context context, List<Item> items, DBHelper dbHelper) {
        this.inflater = LayoutInflater.from(context);
        this.edrItems = items;
        this.dbHelper = dbHelper;

    }

    @Override
    public EdrHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.edr_item, parent,false);
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

        holder.checkBoxGoldStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.checkBoxGoldStar.isChecked()) {
                    new EdrInterestTableUpdate(dbHelper).addInterestingEDRItemToDB(edrItem);
                } else {
                    new EdrInterestTableUpdate(dbHelper).deleteNotInterestingEDRItemFromDB(edrItem.getId());
                }
            }
        });

        holder.bookViewDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return edrItems.size();
    }

    public class EdrHolder extends RecyclerView.ViewHolder{

        private TextView lastName;
        private TextView firstName;
        private TextView workPlace;
        private TextView position;
        private CheckBox checkBoxGoldStar;
        private ImageView bookViewDeclaration;


        public EdrHolder(View itemView) {
            super(itemView);
            lastName = (TextView) itemView.findViewById(R.id.last_name);
            firstName = (TextView) itemView.findViewById(R.id.first_name);
            workPlace = (TextView) itemView.findViewById(R.id.work_place);
            position = itemView.findViewById(R.id.position);
            checkBoxGoldStar = itemView.findViewById(R.id.checkbox_gold_star);
            bookViewDeclaration = itemView.findViewById(R.id.book_view_declaration);
        }
    }
}
