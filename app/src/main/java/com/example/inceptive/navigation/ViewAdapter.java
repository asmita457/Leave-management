package com.example.inceptive.navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by inceptive on 1/8/18.
 */

public class ViewAdapter extends RecyclerView.Adapter {


    private static final int OPTIONAL_VIEWW = 1;

    Context ctx;
    ArrayList<managerpendinglist> pendinglist;

    public ViewAdapter(Context context, ArrayList<managerpendinglist> pendinglist)
    {
        this.ctx=context;
        this.pendinglist=pendinglist;


    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        if (viewType == OPTIONAL_VIEWW) {
            if(pendinglist.isEmpty())
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opt, parent, false);
                ViewAdapter.OptionalViewHolder vh = new ViewAdapter.OptionalViewHolder(v);
                return vh;
            }
            else if(pendinglist.size()>0 || pendinglist.size()==0)
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.optional, parent, false);
                ViewAdapter.OptionalViewHolder vh = new ViewAdapter.OptionalViewHolder(v);
                return vh;
            }
        }
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_manager_home, parent, false);
        My_ViewHolder vh = new My_ViewHolder(v);
        return vh;


//        View v;
//        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_manager_home, parent, false);
//        ManagerAdapter.My_ViewHolder vh = new ManagerAdapter.My_ViewHolder(v);
//        return vh;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        try {
            if (holder instanceof My_ViewHolder) {

                managerpendinglist model3 = pendinglist.get(position);

                ((My_ViewHolder) holder).managerhome_empfirstname.setText(model3.getFirst_name());
                ((My_ViewHolder) holder).managerhome_emplastname.setText(model3.getLast_name());

                ((My_ViewHolder) holder).allleavelinearlayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        managerpendinglist manage = pendinglist.get(position);

                        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx, R.style.AlertDialogTheme);
                        LayoutInflater inflater;

                        inflater = LayoutInflater.from(ctx);
                        final View dialogView = inflater.inflate(R.layout.activity_all_leave_info, null);
                        dialogBuilder.setView(dialogView);
                        dialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

//                                go();
                            }
                        });
                        final AlertDialog  alertDialog = dialogBuilder.create();
//                        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                        alertDialog.setTitle("Detail Information");
                        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface arg0) {
//                                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                            }
                        }); dialogBuilder.setCancelable(true);


                        alertDialog.show();

                        TextView managerhome_empfrom=(TextView)dialogView.findViewById(R.id.managerhome_empfrom);
                        TextView managerhome_empto=(TextView)dialogView.findViewById(R.id.managerhome_empto);
                        final TextView managerhome_empreason=(TextView)dialogView.findViewById(R.id.managerhome_empreason);
                        TextView managerhome_empstatus=(TextView)dialogView.findViewById(R.id.managerhome_empstatus);
                        TextView managerhome_empstatuss=(TextView)dialogView.findViewById(R.id.managerhome_empstatuss);

                         managerhome_empfrom.setText(manage.getFrom_date());
                        managerhome_empto.setText(manage.getTo_date());
                        managerhome_empreason.setText(manage.getReason());
                        managerhome_empstatus.setText(manage.getLeave_status());
                        managerhome_empstatuss.setText(manage.getSts());

                        managerhome_empreason.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                managerpendinglist manage = pendinglist.get(position);
                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx, R.style.AlertDialogTheme);
                                LayoutInflater inflater;

                                inflater = LayoutInflater.from(ctx);
                                final View dialogView = inflater.inflate(R.layout.rsn,null );
                                dialogBuilder.setView(dialogView);

                                final AlertDialog alert =dialogBuilder.create();
                                alert.setTitle("Leave Reason");
                                alert.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

//                                        updateinter.getData();
                                    }
                                });
                                alert.setOnShowListener( new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface arg0) {
//                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                                    }
                                });
                                alert.show();


                                TextView rssnn=(TextView)alert.findViewById(R.id.rssnn);
                                rssnn.setText(manage.getReason());
                                rssnn.setMaxLines(7);

                            }



                        });

                    }
                });

//                My_ViewHolder holder1 = (My_ViewHolder) holder;
//                holder1.bindView(position);
//                setData(holder1, position);
//                holder.act
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemViewType(int position) {
        if (pendinglist.size() == 0) {
            return OPTIONAL_VIEWW;
        }
        return super.getItemViewType(position);
    }
    private void setData(ViewAdapter.My_ViewHolder holder, int position)
    {
        managerpendinglist model = pendinglist.get(position);

        for (int j = 0; j < pendinglist.size();j++)
        {
//            holder.managerhome_leaveid.setText(model.getEmployee_id());
//            holder.managerhome_empfirstname.setText(model.getFirst_name());
//            holder.managerhome_emplastname.setText(model.getLast_name());
//            holder.managerhome_empfrom.setText(model.getFrom_date());
//            holder.managerhome_empto.setText(model.getTo_date());
//            holder.managerhome_empreason.setText(model.getReason());
//            holder.managerhome_empstatus.setText(model.getLeave_status());



        }


    }

    @Override
    public int getItemCount()
    {
        if (pendinglist.size() == 0) {
            return 1;
        } else {
            return pendinglist.size();
        }
    }

    public void FilterList(ArrayList<managerpendinglist> filteredList) {


        pendinglist=filteredList;
        notifyDataSetChanged();
    }

    private class My_ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView managerhome_leaveid,managerhome_emplastname,managerhome_empfirstname,managerhome_empfrom,managerhome_empemail,managerhome_empto,managerhome_lastname,managerhome_firstname,managerhome_empstatus,managerhome_empreason;

        TextView managerhome_empstatuss;
        LinearLayout allleavelinearlayout;
        public My_ViewHolder(View itemView)
        {
            super(itemView);
//            managerhome_leaveid = (TextView)itemView.findViewById(R.id.managerhome_leaveid);
             allleavelinearlayout=(LinearLayout)itemView.findViewById(R.id.allleavelinearlayout);
            managerhome_emplastname = (TextView)itemView.findViewById(R.id.managerhome_emplastname);
            managerhome_empfirstname = (TextView)itemView.findViewById(R.id.managerhome_empfirstname);
            managerhome_empfrom = (TextView)itemView.findViewById(R.id.managerhome_empfrom);
            managerhome_empto = (TextView)itemView.findViewById(R.id.managerhome_empto);
            managerhome_empreason = (TextView)itemView.findViewById(R.id.managerhome_empreason);
            managerhome_empstatus = (TextView)itemView.findViewById(R.id.managerhome_empstatus);
            managerhome_empstatuss=(TextView)itemView.findViewById(R.id.managerhome_empstatuss);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    managerpendinglist cpu = (managerpendinglist) view.getTag();
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
        TextView noRecordFound;

        OptionalViewHolder(View itemView) {
            super(itemView);
            noRecordFound = (TextView) itemView.findViewById(R.id.noRecordFound);
        }
    }

}











