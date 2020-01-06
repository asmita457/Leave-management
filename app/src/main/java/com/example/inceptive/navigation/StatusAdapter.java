package com.example.inceptive.navigation;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inceptive.fragment.LeaveStatus;
import com.example.inceptive.fragment.ProfileAdapter;
import com.example.inceptive.rest.Utils;

import java.util.ArrayList;

/**
 * Created by inceptive on 31/7/18.
 */

public class StatusAdapter extends RecyclerView.Adapter{

    private static final int OPTIONAL_VIEWWWW = 1;

        Context ctx;
        ArrayList<ModelStatus> statuslist;



        public StatusAdapter(Context context, ArrayList<ModelStatus> statuslist)
        {
            this.ctx = context;
            this.statuslist = statuslist;

        }

//        public StatusAdapter(LeaveStatus leaveStatus, ArrayList<ModelStatus> statuslist) {
//        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_status, parent, false);
            com.example.inceptive.navigation.StatusAdapter.My_ViewHolder vh = new com.example.inceptive.navigation.StatusAdapter.My_ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
        {
            try {
                if (holder instanceof StatusAdapter.My_ViewHolder) {
                    ModelStatus stss=statuslist.get(position);

                    ((My_ViewHolder) holder).textstatus.setText(stss.getLeave_type());
                    ((My_ViewHolder) holder).textreason.setText(stss.getReason());

                    ((My_ViewHolder) holder).textreason.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            ModelStatus stsss=statuslist.get(position);
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx,R.style.AlertDialogTheme);
                            LayoutInflater inflater;

                            inflater = LayoutInflater.from(ctx);
                            final View dialogView = inflater.inflate(R.layout.rsn, null);
                            dialogBuilder.setView(dialogView);
                            dialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

//                                go();
                                }
                            });

                            final AlertDialog  alertDialog = dialogBuilder.create();
//                        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                            alertDialog.setTitle("Reason Details");
                            dialogBuilder.setCancelable(true);
                            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(DialogInterface arg0) {
//                                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                                }
                            });

                            alertDialog.show();
                            TextView rssnnn=(TextView)dialogView.findViewById(R.id.rssnn);
                            rssnnn.setText(stsss.getReason());


                        }
                    });

                    ((My_ViewHolder) holder).stslinearlayout.setOnClickListener(new View.OnClickListener() {



                        @Override
                        public void onClick(View v) {
                            ModelStatus stsss=statuslist.get(position);


                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx, R.style.AlertDialogTheme);
                            LayoutInflater inflater;

                            inflater = LayoutInflater.from(ctx);
                            final View dialogView = inflater.inflate(R.layout.statusdialogue, null);
                            dialogBuilder.setView(dialogView);
                            dialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

//                                go();
                                }
                            });
                            final AlertDialog  alertDialog = dialogBuilder.create();
//                        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                            alertDialog.setTitle("Leave Detail");
                            dialogBuilder.setCancelable(true);
                            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(DialogInterface arg0) {
//                                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                                }
                            });

                            alertDialog.show();

                            TextView textfrom_date=(TextView)dialogView.findViewById(R.id.leavestatus_fromDate);
                                 TextView textto_date=(TextView)dialogView.findViewById(R.id.leavestatus_toDate);
                            TextView textapproved_by=(TextView)dialogView.findViewById(R.id.approvedby);

                            textfrom_date.setText(stsss.getFrom_date());
                            textto_date.setText(stsss.getTo_date());

                                textapproved_by.setText(stsss.getApproved_by());
                                }
                    });
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void setData(com.example.inceptive.navigation.StatusAdapter.My_ViewHolder holder, int position)
        {
//            ModelStatus model1 = statuslist.get(position);
            ModelStatus model1=statuslist.get(position);
            ModelStatus st= model1;
//                    Utils.setStringPreferences(ctx,PrefTag.STATUS_LIST,st);
            for (int j = 0; j < statuslist.size();j++)
            {
            int sts=statuslist.size();
                Utils.setStringPreferences(ctx,PrefTag.STATUS_LIST, String.valueOf(sts));
            }
            }

        @Override
        public int getItemCount()
        {
            if (statuslist.size() == 0) {
                return 1;
            }
            else
            {
                return statuslist.size();
            }
        }

        private class My_ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView textleave_id,textfrom_date,textto_date,textstatus,textreason,textapproved_by;

            LinearLayout stslinearlayout;
            public My_ViewHolder(View itemView)
            {
                super(itemView);

                textfrom_date = (TextView)itemView.findViewById(R.id.leavestatus_fromDate);
                textto_date = (TextView)itemView.findViewById(R.id.leavestatus_toDate);
                textreason = (TextView)itemView.findViewById(R.id.reasonstatus);
                textstatus = (TextView)itemView.findViewById(R.id.statusleave);
                textapproved_by=(TextView)itemView.findViewById(R.id.approvedby);

                stslinearlayout=(LinearLayout)itemView.findViewById(R.id.stslinearlayout);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ModelStatus cpu = (ModelStatus) view.getTag();
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


