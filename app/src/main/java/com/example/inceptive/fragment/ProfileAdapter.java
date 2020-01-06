package com.example.inceptive.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inceptive.navigation.ListItem;
import com.example.inceptive.navigation.R;

import java.util.ArrayList;

/**
 * Created by inceptive on 28/7/18.
 */
public class ProfileAdapter extends RecyclerView.Adapter
{
    private static final int OPTIONAL_VIEWWW = 1;

    Context ctx;
    ArrayList<ListItem> profilelist;

//    this.arraylist.addAll(Profile.imageModelArrayList);


    public ProfileAdapter(Context context, ArrayList<ListItem> profilelist)
    {
        this.ctx = context;
        this.profilelist = profilelist;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        if (viewType == OPTIONAL_VIEWWW) {
           if(profilelist.isEmpty())
           {
               v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opt, parent, false);
               ProfileAdapter.OptionalViewHolder vh = new ProfileAdapter.OptionalViewHolder(v);
               return vh;
           }
           else if(profilelist.size()>0 || profilelist.size()==0)
           {
               v = LayoutInflater.from(parent.getContext()).inflate(R.layout.optional, parent, false);
               ProfileAdapter.OptionalViewHolder vh = new ProfileAdapter.OptionalViewHolder(v);
               return vh;
           }
        }
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profilelist, parent, false);
        ProfileAdapter.My_ViewHolder vh = new ProfileAdapter.My_ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        try {
            if (holder instanceof ProfileAdapter.My_ViewHolder) {

                ListItem listt = profilelist.get(position);

                ((My_ViewHolder) holder).textfirstname.setText(listt.getFirst_name());
                ((My_ViewHolder) holder).textlastname.setText(listt.getLast_name());
//                ((My_ViewHolder) holder).textemp_id.setText(listt.getEmp_id());
                ((My_ViewHolder) holder).profilelinearlayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ListItem listtt = profilelist.get(position);


                        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx,R.style.AlertDialogTheme);
                        LayoutInflater inflater;

                        inflater = LayoutInflater.from(ctx);
                        final View dialogView = inflater.inflate(R.layout.profiledialogue, null);
                        dialogBuilder.setView(dialogView);
                        dialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        final AlertDialog  alertDialog = dialogBuilder.create();
                        alertDialog.setTitle("Employee Detail : ");

                        dialogBuilder.setCancelable(true);
                        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface arg0) {
//                                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                            }
                        });
                        alertDialog.show();

                        TextView textemp_id= (TextView)dialogView.findViewById(R.id.textemp_id);
                        TextView textemail=(TextView)dialogView.findViewById(R.id.textemail);
                        TextView textemployee_designation=(TextView)dialogView.findViewById(R.id.textrole);
                        TextView textdate=(TextView)dialogView.findViewById(R.id.textdate);

                        textemp_id.setText(listtt.getEmp_id());
                        textemail.setText(listtt.getEmail());
                        textemployee_designation.setText(listtt.getEmployee_designation());
                        textdate.setText(listtt.getDate());
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemViewType(int position) {
        if (profilelist.size() == 0) {
            return OPTIONAL_VIEWWW;
        }
        return super.getItemViewType(position);
    }

    private void setData(My_ViewHolder holder, int position)
    {
            ListItem model = profilelist.get(position);

        for (int j = 0; j < profilelist.size();j++)
        {
//            holder.textfirstname.setText(model.getFirst_name());
//            holder.textlastname.setText(model.getLast_name());
//            holder.textemail.setText(model.getEmail());
//            holder.textemployee_designation.setText(model.getEmployee_designation());
//            holder.textemp_id.setText(model.getEmp_id());
//            holder.textdate.setText(model.getDate());

        }


    }

    @Override
    public int getItemCount()
    {
        if (profilelist.size() == 0) {
            return 1;
        } else {
            return profilelist.size();
        }
    }



    public void FilterList(ArrayList<ListItem> filteredList) {


        profilelist=filteredList;
        notifyDataSetChanged();
    }




    private class My_ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textfirstname,textlastname,textemail,textemployee_designation,textemp_id,textdate;

        LinearLayout profilelinearlayout;
        public My_ViewHolder(View itemView)
        {
            super(itemView);

            profilelinearlayout=(LinearLayout)itemView.findViewById(R.id.profilelinearlayout);

            textfirstname = (TextView)itemView.findViewById(R.id.textfirstname);
            textlastname = (TextView)itemView.findViewById(R.id.textlastname);
            textemail = (TextView)itemView.findViewById(R.id.textemail);
            textemployee_designation=(TextView)itemView.findViewById(R.id.textrole);
//            textemp_id=(TextView)itemView.findViewById(R.id.textemp_id);
            textdate=(TextView)itemView.findViewById(R.id.textdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ListItem cpu = (ListItem) view.getTag();
//
//                    Toast.makeText(view.getContext(), cpu.getFirst_name()+" "+cpu.getLast_name()+" Email "+ cpu.getEmail(), Toast.LENGTH_SHORT).show();

                }
            });


        }



        public void bindView(int position)
        {

        }
    }

    public class OptionalViewHolder extends RecyclerView.ViewHolder {
        TextView noRecordFoundd;

        OptionalViewHolder(View itemView) {
            super(itemView);
            noRecordFoundd = (TextView) itemView.findViewById(R.id.noRecordFound);
        }
    }

}
