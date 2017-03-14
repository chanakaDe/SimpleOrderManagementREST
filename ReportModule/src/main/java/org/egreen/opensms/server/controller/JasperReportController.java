//package org.egreen.opensms.server.controller;
//
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRTableModelDataSource;
//import org.apache.commons.collections.map.HashedMap;
//import org.apache.commons.io.IOUtils;
//import org.egreen.opensms.server.entity.*;
//
//import org.egreen.opensms.server.models.*;
//import org.egreen.opensms.server.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.net.URL;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
//* Created by pramoda-nf on 10/30/15.
//*/
//
//
//@Controller
//@RequestMapping("openmicro/v1/jasperReport")
//public class JasperReportController {
//
//
//    @Autowired
//    private TableDAOService customerDAOService;
//
//
//    /**
//     * getRepaymentSheet
//     *
//     * @param session
//     * @param response
//     */
//    @RequestMapping(value = "getRepaymentSheet", method = RequestMethod.GET)
//    public void getRepaymentSheet(HttpSession session, HttpServletResponse response,
//                                  @RequestParam("centerId") String centerId, @RequestParam("img") String img) {
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        map.put("noOfNotPaid", "");
//        map.put("notPaidAmount", "");
//        map.put("noOfUnderPayment", "");
//        map.put("underPaymentAmount", "");
//        map.put("noOfSettlement", "");
//        map.put("settlementAmount", "");
//        map.put("slipNo", "");
//
//        Center center = centerDAOService.getCenterById(centerId);
//        if (center != null) {
//
//            map.put("center", centerId + "-" + center.getName());
//        }
//        map.put("package", "");
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("isFieldHidden");
//        model.addColumn("memCode");
//        model.addColumn("memName");
//        model.addColumn("loanAmount");
//        model.addColumn("dueAmount");
//        model.addColumn("loanBalance");
//        model.addColumn("newLoan");
//        model.addColumn("amt1");
//        model.addColumn("amt2");
//        model.addColumn("amt3");
//        model.addColumn("amt4");
//        model.addColumn("amt5");
//        model.addColumn("amt6");
//        model.addColumn("amt7");
//        model.addColumn("amt8");
//        model.addColumn("group");
//
//        Map<String, List<GroupHasMember>> membersMap = new HashedMap();
//        List<GroupHasMember> groupHasMemberList = groupHasMemberDAOService.getGroupByCenterId(centerId);
//        for (GroupHasMember groupHasMember : groupHasMemberList) {
//
//
////            Loan loan = loanDAOService.getLoanByLoanCode(loanOutStandingModel.getLoanCode());
////            if (loan!=null){
////                List<RepaymentDetail> detailByLoanId = repaymentDetailDAOService.getDetailByLoanId(loan.getLoanid());
////                for (RepaymentDetail repaymentDetail : detailByLoanId) {
////                    paymentAmount.put(repaymentDetail.getLoanId(),repaymentDetail.getPaymentAmount().doubleValue());
////                }
////            }
////            paymentAmount.get(loan.getLoanid());
//
//            String memberId = groupHasMember.getMemberid();
//            List<GroupHasMember> groupHasMemberById = groupHasMemberDAOService.getGroupMembersByMemberId(memberId);
//            membersMap.put(groupHasMember.getGroupid(), groupHasMemberById);
//        }
//
//        List<String> keyList = new ArrayList<String>(membersMap.keySet());
//        Collections.sort(keyList, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                Groups detailsById1 = groupDAOService.getDetailsById(o1);
//                Groups detailsById2 = groupDAOService.getDetailsById(o2);
//                if (Integer.parseInt(detailsById1.getGroupRefId()) > Integer.parseInt(detailsById2.getGroupRefId())) {
//                    return 1;
//                }
//                return -1;
//            }
//        });
//
//        List<Table> allCustomersByCenterId = customerDAOService.getAllCustomersByCenterId(centerId, null, null);
//        final Map<String, Table> customerMap = new HashedMap();
//        int count = 0;
//        for (Table customer : allCustomersByCenterId) {
//            customerMap.put(customer.getCustomerid(), customer);
//        }
//        for (String string : keyList) {
//            List<GroupHasMember> groupHasMembers = membersMap.get(string);
//            Collections.sort(groupHasMembers, new Comparator<GroupHasMember>() {
//                @Override
//                public int compare(GroupHasMember o1, GroupHasMember o2) {
//                    Table c1 = customerMap.get(o1.getMemberid());
//                    Table c2 = customerMap.get(o2.getMemberid());
//
//
//                    if (c1.getSortingValue() > c2.getSortingValue()) {
//                        return 1;
//                    }
//                    return -1;
//                }
//            });
//
//
//            if (count == 4) {
//                model.addRow(new Object[]{false, "",
//                        " ",
//                        0.0,
//                        "",
//                        "", "", "", "", "", "", "", "", "", "", ""});
//                model.addRow(new Object[]{false, "",
//                        " ",
//                        0.0,
//                        "",
//                        "", "", "", "", "", "", "", "", "", "", ""});
//                model.addRow(new Object[]{false, "",
//                        " ",
//                        0.0,
//                        "",
//                        "", "", "", "", "", "", "", "", "", "", ""});
//
//            }
//
//            String groupName = groupDAOService.getGroupNameById(string);
//            model.addRow(new Object[]{true, "",
//                    "",
//                    "",
//                    "",
//                    "", "", "", "", "", "", "", "", "", "", "Group " + groupName});
//
//            count++;
//            for (GroupHasMember groupHasMember : groupHasMembers) {
//
//
//                Loan loan = loanDAOService.getOpenLoanByMemberId(groupHasMember.getMemberid());
//                if (loan != null) {
//                    LoanOutStandingModel loanOutstandingModel1 = getLoanOutstandingModel(loan.getLoanid());
//                    //  System.out.println("Member Code : "+loanOutstandingModel1.getMemberCode());
//                    if (loanOutstandingModel1 != null) {
//                        model.addRow(new Object[]{false, loanOutstandingModel1.getMemberCode(),
//                                loanOutstandingModel1.getMemberName(),
//                                loanOutstandingModel1.getLoanAmount().doubleValue(),
//                                loanOutstandingModel1.getDue() + "",
//                                loanOutstandingModel1.getBalance() + "", "", "", "", "", "", "", "", "", ""});
//
//                    }
//
//                }
//
//            }
//            model.addRow(new Object[]{false, "",
//                    "GROUP TOTAL",
//                    0.0,
//                    "",
//                    "", "", "", "", "", "", "", "", "", "", ""});
//
//        }
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("RepaymentSheet.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, true);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                String pattern = "yyyyMMdd";
//                SimpleDateFormat format = new SimpleDateFormat(pattern);
////                try {
////                    Date date = format.parse("12/31/2006");
////                    System.out.println(date);
////                } catch (ParseException e) {
////                    e.printStackTrace();
////                }
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + centerId + "/" + format.format(new Date()) + ".pdf");
////
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    /**
//     * getRepaymentSheetModel
//     *
//     * @param centerId
//     * @return
//     */
//    @RequestMapping(value = "getRepaymentSheetModel", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public List<Object[]> getRepaymentSheetModel(@RequestParam("centerId") String centerId) {
//        List<Object[]> reportModel = new ArrayList<Object[]>();
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("noOfNotPaid", "");
//        map.put("notPaidAmount", "");
//        map.put("noOfUnderPayment", "");
//        map.put("underPaymentAmount", "");
//        map.put("noOfSettlement", "");
//        map.put("settlementAmount", "");
//        map.put("slipNo", "");
//        map.put("center", centerId);
//        map.put("package", "");
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("isFieldHidden");
//        model.addColumn("memCode");
//        model.addColumn("memName");
//        model.addColumn("loanAmount");
//        model.addColumn("dueAmount");
//        model.addColumn("loanBalance");
//        model.addColumn("newLoan");
//        model.addColumn("amt1");
//        model.addColumn("amt2");
//        model.addColumn("amt3");
//        model.addColumn("amt4");
//        model.addColumn("amt5");
//        model.addColumn("amt6");
//        model.addColumn("amt7");
//        model.addColumn("amt8");
//        model.addColumn("group");
//
//        Map<String, List<GroupHasMember>> membersMap = new HashedMap();
//        List<GroupHasMember> groupHasMemberList = groupHasMemberDAOService.getGroupByCenterId(centerId);
//        for (GroupHasMember groupHasMember : groupHasMemberList) {
//
////            Loan loan = loanDAOService.getLoanByLoanCode(loanOutStandingModel.getLoanCode());
////            if (loan!=null){
////                List<RepaymentDetail> detailByLoanId = repaymentDetailDAOService.getDetailByLoanId(loan.getLoanid());
////                for (RepaymentDetail repaymentDetail : detailByLoanId) {
////                    paymentAmount.put(repaymentDetail.getLoanId(),repaymentDetail.getPaymentAmount().doubleValue());
////                }
////            }
////            paymentAmount.get(loan.getLoanid());
//
//            String memberId = groupHasMember.getMemberid();
//            List<GroupHasMember> groupHasMemberById = groupHasMemberDAOService.getGroupMembersByMemberId(memberId);
//            membersMap.put(groupHasMember.getGroupid(), groupHasMemberById);
//        }
//
//        List<String> keyList = new ArrayList<String>(membersMap.keySet());
//        Collections.sort(keyList, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                Groups detailsById1 = groupDAOService.getDetailsById(o1);
//                Groups detailsById2 = groupDAOService.getDetailsById(o2);
//                if (Integer.parseInt(detailsById1.getGroupRefId()) > Integer.parseInt(detailsById2.getGroupRefId())) {
//                    return 1;
//                }
//                return -1;
//            }
//        });
//
//        List<Table> allCustomersByCenterId = customerDAOService.getAllCustomersByCenterId(centerId, null, null);
//        final Map<String, Table> customerMap = new HashedMap();
//        int count = 0;
//        for (Table customer : allCustomersByCenterId) {
//            customerMap.put(customer.getCustomerid(), customer);
//        }
//        for (String string : keyList) {
//            List<GroupHasMember> groupHasMembers = membersMap.get(string);
//            Collections.sort(groupHasMembers, new Comparator<GroupHasMember>() {
//                @Override
//                public int compare(GroupHasMember o1, GroupHasMember o2) {
//                    Table c1 = customerMap.get(o1.getMemberid());
//                    Table c2 = customerMap.get(o2.getMemberid());
//                    if (c1.getSortingValue() > c2.getSortingValue()) {
//                        return 1;
//                    }
//                    return -1;
//                }
//            });
//
//            if (count == 4) {
//                for (int i = 0; i < 3; i++) {
//                    Object[] objects = {false, "",
//                            " ",
//                            0.0,
//                            "",
//                            "", "", "", "", "", "", "", "", "", "", ""};
//                    model.addRow(objects);
//                    reportModel.add(objects);
//                }
//            }
//            String groupName = groupDAOService.getGroupNameById(string);
//            model.addRow(new Object[]{true, "",
//                    "",
//                    "",
//                    "",
//                    "", "", "", "", "", "", "", "", "", "", "Group " + groupName});
//            reportModel.add(new Object[]{true, "",
//                    "",
//                    "",
//                    "",
//                    "", "", "", "", "", "", "", "", "", "", "Group " + groupName});
//            count++;
//            for (GroupHasMember groupHasMember : groupHasMembers) {
//                Loan loan = loanDAOService.getOpenLoanByMemberId(groupHasMember.getMemberid());
//                if (loan != null) {
//                    LoanOutStandingModel loanOutstandingModel1 = getLoanOutstandingModel(loan.getLoanid());
//                    if (loanOutstandingModel1 != null) {
//                        Object[] objects = {false, loanOutstandingModel1.getMemberCode(),
//                                loanOutstandingModel1.getMemberName(),
//                                loanOutstandingModel1.getLoanAmount().doubleValue(),
//                                loanOutstandingModel1.getDue() + "",
//                                loanOutstandingModel1.getBalance() + "", "", "", "", "", "", "", "", "", ""};
//                        model.addRow(objects);
//                        reportModel.add(objects);
//                    }
//                }
//            }
//
//            model.addRow(new Object[]{false, "",
//                    "GROUP TOTAL",
//                    0.0,
//                    "",
//                    "", "", "", "", "", "", "", "", "", "", ""});
//            reportModel.add(new Object[]{false, "",
//                    "GROUP TOTAL",
//                    0.0,
//                    "",
//                    "", "", "", "", "", "", "", "", "", "", ""});
//        }
//        return reportModel;
//
//    }
//
//
//    @RequestMapping(value = "getCollectionTotal", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public BigDecimal getCollectionTotal(@RequestParam("branchId") String branchId) {
//        Double totalCollection = 0.0;
//        Map<String, List<GroupHasMember>> membersMap = new HashedMap();
//        List<GroupHasMember> groupHasMemberList = groupHasMemberDAOService.getGroupByBranchId(branchId);
//        //  System.out.println(groupHasMemberList);
////        for (GroupHasMember groupHasMember : groupHasMemberList) {
////            String memberId = groupHasMember.getMemberid();
////            List<GroupHasMember> groupHasMemberById = groupHasMemberDAOService.getGroupMembersByMemberId(memberId);
////            membersMap.put(groupHasMember.getGroupid(), groupHasMemberById);
////        }
////
////      List<String> keyList = new ArrayList<String>(membersMap.keySet());
////        Collections.sort(keyList, new Comparator<String>() {
////            @Override
////            public int compare(String o1, String o2) {
////                Groups detailsById1 = groupDAOService.getDetailsById(o1);
////                Groups detailsById2 = groupDAOService.getDetailsById(o2);
////                if (Integer.parseInt(detailsById1.getGroupRefId()) > Integer.parseInt(detailsById2.getGroupRefId())) {
////                    return 1;
////                }
////                return -1;
////            }
//        //  });
//
////        List<Customer> allCustomersByCenterId = customerDAOService.getAllCustomersByBranchId(branchId, null, null);
////        final Map<String, Customer> customerMap = new HashedMap();
////
////        for (Customer customer : allCustomersByCenterId) {
////            customerMap.put(customer.getCustomerid(), customer);
////        }
////        for (String string : keyList) {
////            List<GroupHasMember> groupHasMembers = membersMap.get(string);
////            Collections.sort(groupHasMembers, new Comparator<GroupHasMember>() {
////                @Override
////                public int compare(GroupHasMember o1, GroupHasMember o2) {
////                    Customer c1 = customerMap.get(o1.getMemberid());
////                    Customer c2 = customerMap.get(o2.getMemberid());
////                    if (c1.getSortingValue() > c2.getSortingValue()) {
////                        return 1;
////                    }
////                    return -1;
////                }
////            });
//
//
//        for (GroupHasMember groupHasMember : groupHasMemberList) {
//            Loan loan = loanDAOService.getOpenLoanByMemberId(groupHasMember.getMemberid());
//            if (loan != null) {
//                BigDecimal loanOutstandingModel1 = getDueAmount(loan.getLoanid());
//                if (loanOutstandingModel1 != null) {
//
//                    totalCollection += loanOutstandingModel1.doubleValue();
//                }
//
//            }
//        }
//
//
//        //}
//        return BigDecimal.valueOf(totalCollection);
//
//    }
//
//    /**
//     * Get Due Amount
//     *
//     * @param loanId
//     * @return
//     */
//    private BigDecimal getDueAmount(String loanId) {
//        Loan loan = loanDAOService.getDetailsById(loanId);
//        if (loan != null) {
//            if (loan != null) {
////                RepaymentDetail repaymentDetail = repaymentDetailDAOService.getLastRepaymentDetail(loan.getLoanid());
////                if (repaymentDetail != null) {
//                double totalPaybleAmount = 0;
//                if (loan.getPackageid() != null && !loan.getPackageid().isEmpty()) {
//                    org.egreen.opensms.server.entity.Package packager = packageDAOService.getPackageById(loan.getPackageid());
//                    if (packager != null && packager.getRate() != null) {
//                        totalPaybleAmount = loan.getAmount().doubleValue() * (packager.getRate().doubleValue() + 100) / 100;
//                        double value = totalPaybleAmount / packager.getPeriod();
//                        double dueAmount = Math.round(value * 100.0) / 100.0;
//                        return BigDecimal.valueOf(dueAmount);
//                    }
//                }
////                }
//            }
//        }
//        return BigDecimal.ZERO;
//    }
//
//
//    /**
//     * Get LoanOutStandingModel
//     *
//     * @param loanId
//     * @return
//     */
//    private LoanOutStandingModel getLoanOutstandingModel(String loanId) {
//        // JRTableModelDataSource ds = null;
//
//        //  Map<String, Object> map = new HashMap<String, Object>();
//
//        //List<LoanOutStandingModel> loanOutStandingModelList = new ArrayList<LoanOutStandingModel>();
//        LoanOutStandingModel loanOutStandingModel = new LoanOutStandingModel();
//        Loan loan = loanDAOService.getDetailsById(loanId);
//        if (loan != null) {
//
//            if (loan != null) {
//                if (!loan.getCustomerid().isEmpty()) {
//                    Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//                    if (customer != null) {
//
//                        List<GroupHasMember> groupMembersByMemberId = groupHasMemberDAOService.getGroupMembersByMemberId(customer.getCustomerid());
//
//                        //Set Group Id
//                        loanOutStandingModel.setGroupId(groupMembersByMemberId.get(0).getGroupid());
//
//                        //Set Customer Id
//                        loanOutStandingModel.setMemberId(customer.getCustomerid());
//
//                        //Set Customer Code
//                        loanOutStandingModel.setMemberCode(customer.getCode());
//
//                        //Set Customer Name
//                        loanOutStandingModel.setMemberName(customer.getInitials());
//
//
//                        //Set Customer Nic
//                        loanOutStandingModel.setMemberNic(customer.getNic());
//
//                        //Set Customer TelePhone Number
//                        loanOutStandingModel.setMemberTPNum(customer.getTpNo());
//                    }
//                }
//
//                //Set Loan Code
//                loanOutStandingModel.setLoanCode(loan.getLoanCode());
//
//                //Set Loan Date
//                loanOutStandingModel.setLoanDate(loan.getLoanDate());
//
//                //Set Loan Amount
//                loanOutStandingModel.setLoanAmount(loan.getAmount());
//
//                RepaymentDetail repaymentDetail = repaymentDetailDAOService.getLastRepaymentDetail(loan.getLoanid());
//                if (repaymentDetail != null) {
//
//                    //Set PayDate
//                    loanOutStandingModel.setLastPayDate(repaymentDetail.getPayDate());
//
////                    if (loan.getPackageid() != null && !loan.getPackageid().isEmpty()) {
////                        org.egreen.opensms.server.entity.Package packager = packageDAOService.getPackageById(loan.getPackageid());
////                        if (packager != null && packager.getRate() != null) {
////                            double dueAmount = loan.getAmount().doubleValue() * packager.getRate().doubleValue() / 100;
////
////                            //Set Due Amount
////                            loanOutStandingModel.setDue(BigDecimal.valueOf(dueAmount));
////                        }
////                    }
//                }
//
//                //Get Total Pay Amounts
//                double payAmount = 0;
//                List<RepaymentDetail> detailByLoanId = repaymentDetailDAOService.getDetailByLoanId(loan.getLoanid());
//                for (RepaymentDetail detail : detailByLoanId) {
//                    if (detail.getPaymentAmount() != null) {
//                        payAmount += detail.getPaymentAmount().doubleValue();
//                    }
//                }
//                //Set Total Pay Amount
//                loanOutStandingModel.setPayAmount(BigDecimal.valueOf(payAmount));
//
//                //Get Total Payble Amount
//                double totalPaybleAmount = 0;
//                if (loan.getPackageid() != null && !loan.getPackageid().isEmpty()) {
//                    org.egreen.opensms.server.entity.Package packager = packageDAOService.getPackageById(loan.getPackageid());
//                    if (packager != null && packager.getRate() != null) {
//
//                        totalPaybleAmount = loan.getAmount().doubleValue() * (packager.getRate().doubleValue() + 100) / 100;
//
//
//                        double value = totalPaybleAmount / packager.getPeriod();
//
////                        DecimalFormat df=new DecimalFormat("0.00");
////                        String formate = df.format(value);
////                        double dueAmount = 0;
////                        try {
////                            dueAmount = (Double)df.parse(formate);
////                        } catch (ParseException e) {
////                            e.printStackTrace();
////                        }
//                        double dueAmount = Math.round(value * 100.0) / 100.0;
//
//                        //Set Due Amount
//                        loanOutStandingModel.setDue(BigDecimal.valueOf(dueAmount));
//
//                        //Get Capital Payment
//                        double capitalPayment = totalPaybleAmount / 100 * (100 - packager.getRate().doubleValue());
//
//                        //Get Capital Balance
//                        double capitalBalance = loan.getAmount().doubleValue() - capitalPayment;
//
//                        //Set Capital Balance
//                        loanOutStandingModel.setCapBalance(BigDecimal.valueOf(capitalBalance));
//
//                        //Get Interrest Payment
//                        double interrestPayment = totalPaybleAmount / 100 * packager.getRate().doubleValue();
//
//                        //Get Interrest Balance
//                        double interrestBalance = (loan.getAmount().doubleValue() * packager.getRate().doubleValue() / 100) - interrestPayment;
//
//                        //Set Interrest Balance
//                        loanOutStandingModel.setInsBalance(BigDecimal.valueOf(interrestBalance));
//
//                    }
//                }
//
//                //Get Balance Amount
//                double balance = totalPaybleAmount - payAmount;
//
//                //Set Balance Amount
//                loanOutStandingModel.setBalance(BigDecimal.valueOf(balance));
//            }
//
//            //Add Model To List
//            // loanOutStandingModelList.add(loanOutStandingModel);
//        }
//
//        return loanOutStandingModel;
//    }
//
//
//    /**
//     * getLoanOutstandingReport
//     */
//    @RequestMapping(value = "getLoanOutstandingReport", method = RequestMethod.GET)
//    public void getLoanOutstandingReport(HttpSession session,
//                                         HttpServletResponse response,
//                                         @RequestParam("centerId") String centerId, @RequestParam("img") String img) {
//        JRTableModelDataSource ds = null;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        List<LoanOutStandingModel> loanOutStandingModelList = new ArrayList<LoanOutStandingModel>();
//        List<Loan> loanByCenterId = loanDAOService.getAllLoanByCenterId(centerId, null, null);
//        for (Loan loan : loanByCenterId) {
//            LoanOutStandingModel loanOutStandingModel = new LoanOutStandingModel();
//            if (loan != null) {
//                if (!loan.getCustomerid().isEmpty()) {
//                    Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//                    if (customer != null) {
//                        //Set Customer Code
//                        loanOutStandingModel.setMemberCode(customer.getCode());
//
//                        //Set Customer Nic
//                        loanOutStandingModel.setMemberNic(customer.getNic());
//
//                        //Set Customer TelePhone Number
//                        loanOutStandingModel.setMemberTPNum(customer.getTpNo());
//                    }
//                }
//
//                //Set Loan Code
//                loanOutStandingModel.setLoanCode(loan.getLoanCode());
//
//                //Set Loan Date
//                loanOutStandingModel.setLoanDate(loan.getLoanDate());
//
//                //Set Loan Amount
//                loanOutStandingModel.setLoanAmount(loan.getAmount());
//
//                RepaymentDetail repaymentDetail = repaymentDetailDAOService.getLastRepaymentDetail(loan.getLoanid());
//                if (repaymentDetail != null) {
//
//                    //Set PayDate
//                    loanOutStandingModel.setLastPayDate(repaymentDetail.getPayDate());
//
//                    //Set Due Amount
//                    loanOutStandingModel.setDue(repaymentDetail.getDueAmount());
//                }
//
//                //Get Total Pay Amounts
//                double payAmount = 0;
//                List<RepaymentDetail> detailByLoanId = repaymentDetailDAOService.getDetailByLoanId(loan.getLoanid());
//                for (RepaymentDetail detail : detailByLoanId) {
//                    if (detail.getPaymentAmount() != null) {
//                        payAmount += detail.getPaymentAmount().doubleValue();
//                    }
//                }
//                //Set Total Pay Amount
//                loanOutStandingModel.setPayAmount(BigDecimal.valueOf(payAmount));
//
//                //Get Total Payble Amount
//                double totalPaybleAmount = 0;
//                if (loan.getPackageid() != null && !loan.getPackageid().isEmpty()) {
//                    org.egreen.opensms.server.entity.Package packager = packageDAOService.getPackageById(loan.getPackageid());
//                    if (packager != null && packager.getRate() != null) {
//                        totalPaybleAmount = loan.getAmount().doubleValue() * (packager.getRate().doubleValue() + 100) / 100;
//
//                        //Get Capital Payment
//                        double capitalPayment = totalPaybleAmount / 100 * (100 - packager.getRate().doubleValue());
//
//                        //Get Capital Balance
//                        double capitalBalance = loan.getAmount().doubleValue() - capitalPayment;
//
//                        //Set Capital Balance
//                        loanOutStandingModel.setCapBalance(BigDecimal.valueOf(capitalBalance));
//
//                        //Get Interrest Payment
//                        double interrestPayment = totalPaybleAmount / 100 * packager.getRate().doubleValue();
//
//                        //Get Interrest Balance
//                        double interrestBalance = (loan.getAmount().doubleValue() * packager.getRate().doubleValue() / 100) - interrestPayment;
//
//                        //Set Interrest Balance
//                        loanOutStandingModel.setInsBalance(BigDecimal.valueOf(interrestBalance));
//
//                    }
//                }
//
//                //Get Balance Amount
//                double balance = totalPaybleAmount - payAmount;
//
//                //Set Balance Amount
//                loanOutStandingModel.setBalance(BigDecimal.valueOf(balance));
//            }
//
//            //Add Model To List
//            loanOutStandingModelList.add(loanOutStandingModel);
//
//
//        }
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("memberNo");
//        model.addColumn("nIdNo");
//        model.addColumn("tpNo");
//        model.addColumn("loanNo");
//        model.addColumn("loanDate");
//        model.addColumn("lastPay");
//        model.addColumn("due");
//        model.addColumn("loanAmount");
//        model.addColumn("payAmount");
//        model.addColumn("balance");
//        model.addColumn("capitalBalance");
//        model.addColumn("intBalance");
//
//
//        for (LoanOutStandingModel loanOutStandingModel : loanOutStandingModelList) {
//            model.addRow(new Object[]{loanOutStandingModel.getMemberCode(),
//                    loanOutStandingModel.getMemberNic(), loanOutStandingModel.getMemberTPNum(), loanOutStandingModel.getLoanCode(),
//                    loanOutStandingModel.getLoanDate() + "", loanOutStandingModel.getLastPayDate() + "", loanOutStandingModel.getDue() + "",
//                    loanOutStandingModel.getLoanAmount() + "", loanOutStandingModel.getPayAmount() + "", loanOutStandingModel.getBalance() + "", loanOutStandingModel.getCapBalance() + "",
//                    loanOutStandingModel.getInsBalance() + ""});
//        }
//        ds = new JRTableModelDataSource(model);
//
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanOutstandingReport.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=OutStandingReport.pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//        //return loanOutStandingModelList;
//    }
//
//
//    /**
//     * getLoanOutstandingReportForTax
//     *
//     * @param session
//     * @param response
//     * @param centerId
//     * @param userLevel
//     * @return
//     */
//    @RequestMapping(value = "getLoanOutstandingReportForTax", method = RequestMethod.GET)
//    public void getLoanOutstandingReportForTax(HttpSession session,
//                                               HttpServletResponse response,
//                                               @RequestParam("centerId") String centerId,
//                                               @RequestParam("userLevel") Integer userLevel) {
//        JRTableModelDataSource ds = null;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        List<LoanOutStandingModel> loanOutStandingModelList = new ArrayList<LoanOutStandingModel>();
//        List<Loan> loanByCenterId = loanDAOService.getAllLoanByCenterId(centerId, null, null);
//        for (Loan loan : loanByCenterId) {
//            LoanOutStandingModel loanOutStandingModel = new LoanOutStandingModel();
//            if (loan != null) {
//                if (!loan.getCustomerid().isEmpty()) {
//                    Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//                    if (customer != null) {
//                        //Set Customer Code
//                        loanOutStandingModel.setMemberCode(customer.getCode());
//
//                        //Set Customer Nic
//                        loanOutStandingModel.setMemberNic(customer.getNic());
//
//                        //Set Customer TelePhone Number
//                        loanOutStandingModel.setMemberTPNum(customer.getTpNo());
//                    }
//                }
//
//                //Set Loan Code
//                loanOutStandingModel.setLoanCode(loan.getLoanCode());
//
//                //Set Loan Date
//                loanOutStandingModel.setLoanDate(loan.getLoanDate());
//
//                //Set Loan Amount
//                loanOutStandingModel.setLoanAmount(loan.getAmount());
//
//                RepaymentDetail repaymentDetail = repaymentDetailDAOService.getLastRepaymentDetail(loan.getLoanid());
//                if (repaymentDetail != null) {
//
//                    //Set PayDate
//                    loanOutStandingModel.setLastPayDate(repaymentDetail.getPayDate());
//
//                    //Set Due Amount
//                    loanOutStandingModel.setDue(repaymentDetail.getDueAmount());
//                }
//
//                //Get Total Pay Amounts
//                double payAmount = 0;
//                List<RepaymentDetail> detailByLoanId = repaymentDetailDAOService.getDetailByLoanId(loan.getLoanid());
//                for (RepaymentDetail detail : detailByLoanId) {
//                    if (detail.getPaymentAmount() != null) {
//                        payAmount += detail.getPaymentAmount().doubleValue();
//                    }
//                }
//                //Set Total Pay Amount
//                loanOutStandingModel.setPayAmount(BigDecimal.valueOf(payAmount));
//
//                //Get Total Payble Amount
//                double totalPaybleAmount = 0;
//                if (loan.getPackageid() != null && !loan.getPackageid().isEmpty()) {
//                    org.egreen.opensms.server.entity.Package packager = packageDAOService.getPackageById(loan.getPackageid());
//                    if (packager != null && packager.getRate() != null) {
//                        totalPaybleAmount = loan.getAmount().doubleValue() * (packager.getRate().doubleValue() + 100) / 100;
//
//                        //Get Capital Payment
//                        double capitalPayment = totalPaybleAmount / 100 * (100 - packager.getRate().doubleValue());
//
//                        //Get Capital Balance
//                        double capitalBalance = loan.getAmount().doubleValue() - capitalPayment;
//
//                        //Set Capital Balance
//                        loanOutStandingModel.setCapBalance(BigDecimal.valueOf(capitalBalance));
//
//                        //Get Interrest Payment
//                        double interrestPayment = totalPaybleAmount / 100 * packager.getRate().doubleValue();
//
//                        //Get Interrest Balance
//                        double interrestBalance = (loan.getAmount().doubleValue() * packager.getRate().doubleValue() / 100) - interrestPayment;
//
//                        //Set Interrest Balance
//                        loanOutStandingModel.setInsBalance(BigDecimal.valueOf(interrestBalance));
//                    }
//                }
//                //Get Balance Amount
//                double balance = totalPaybleAmount - payAmount;
//
//                //Set Balance Amount
//                loanOutStandingModel.setBalance(BigDecimal.valueOf(balance));
//            }
//
//            //Add Model To List
//            loanOutStandingModelList.add(loanOutStandingModel);
//        }
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("memberNo");
//        model.addColumn("nIdNo");
//        model.addColumn("tpNo");
//        model.addColumn("loanNo");
//        model.addColumn("loanDate");
//        model.addColumn("lastPay");
//        model.addColumn("due");
//        model.addColumn("loanAmount");
//        model.addColumn("payAmount");
//        model.addColumn("balance");
//        model.addColumn("capitalBalance");
//        model.addColumn("intBalance");
//
//
//        if (userLevel == null && loanOutStandingModelList.size() > 5) {
//            for (int i = 0; i < 5; i++) {
//                loanOutStandingModelList.remove(loanOutStandingModelList.size() - 1);
//            }
//        }
//
//
//        for (LoanOutStandingModel loanOutStandingModel : loanOutStandingModelList) {
//            model.addRow(new Object[]{loanOutStandingModel.getMemberCode(),
//                    loanOutStandingModel.getMemberNic(), loanOutStandingModel.getMemberTPNum(), loanOutStandingModel.getLoanCode(),
//                    loanOutStandingModel.getLoanDate() + "", loanOutStandingModel.getLastPayDate() + "", loanOutStandingModel.getDue() + "",
//                    loanOutStandingModel.getLoanAmount() + "", loanOutStandingModel.getPayAmount() + "", loanOutStandingModel.getBalance() + "", loanOutStandingModel.getCapBalance() + "",
//                    loanOutStandingModel.getInsBalance() + ""});
//        }
//        ds = new JRTableModelDataSource(model);
//
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanOutstandingReportTax.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=OutStandingReport.pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//        // return loanOutStandingModelList;
//    }
//
//
//    /**
//     * getLegderReport
//     *
//     * @param session
//     * @param response
//     * @param loanId
//     */
//    @RequestMapping(value = "getLegderReport", method = RequestMethod.GET)
//    public void bookingInvoice(HttpSession session, HttpServletResponse response,
//                               @RequestParam("loanId") String loanId, @RequestParam("img") String img) {
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        if (loanId != null && !loanId.isEmpty()) {
//            LedgerReport ledgerReport = loanReportController.getLedgerReport(loanId);
//            if (ledgerReport != null) {
//
//
//                map.put("loanNo", ledgerReport.getLoanCode());
//                map.put("loanDate", ledgerReport.getLoanDate() + "");
//                map.put("loanAmount", ledgerReport.getLoanAmount() + "");
//
//                double totalLoanAmount = 0;
//                if (ledgerReport.getLoanAmount() != 0 && ledgerReport.getInterestRate() != 0) {
//                    double loanInterrestAmount = ledgerReport.getLoanAmount() * ledgerReport.getInterestRate() / 100;
//                    totalLoanAmount = ledgerReport.getLoanAmount() + loanInterrestAmount;
//                }
//
//                map.put("withInterest", totalLoanAmount + "" == null ? "__" : totalLoanAmount + "");
//                map.put("memNo", ledgerReport.getCustomerCode() == null ? "__" : ledgerReport.getCustomerCode());
//                map.put("memName", ledgerReport.getCustomerName() == null ? "__" : ledgerReport.getCustomerName());
//                map.put("idNo", ledgerReport.getNic() == null ? "__" : ledgerReport.getNic());
//                map.put("telNo", ledgerReport.getTeleNum() == null ? "__" : ledgerReport.getTeleNum());
//
//                map.put("busiType", ledgerReport.getBusinessType() == null ? "__" : ledgerReport.getBusinessType());
//
//
//                map.put("loanType", ledgerReport.getPackageName() == null ? "__" : ledgerReport.getPackageName());
//                map.put("loanIndex", ledgerReport.getLoanId() == null ? "__" : ledgerReport.getLoanId());
//                map.put("interest", ledgerReport.getInterestRate() + "" == null ? "__" : ledgerReport.getInterestRate() + "");
//
//                map.put("loanBalance", ledgerReport.getLoanBalance() + "" == null ? "__" : ledgerReport.getLoanBalance() + "");
//                map.put("total Paid", ledgerReport.getTotalPaid() + "" == null ? "__" : ledgerReport.getTotalPaid() + "");
//
//                map.put("officePay", "0");
//                map.put("underPay", "0");
//                map.put("notPaid", "0");
//                map.put("printedBy", "__");
//
//
//                DefaultTableModel model = new DefaultTableModel();
//                JTable table = new JTable(model);
//
//                model.addColumn("dueDate");
//                model.addColumn("payDate");
//                model.addColumn("dueAmount");
//                model.addColumn("rate");
//                model.addColumn("reciptNo");
//                model.addColumn("payAmount");
//                model.addColumn("totalPaid");
//                model.addColumn("balance");
//
//                List<RepaymentDetail> repaymentDetailList = ledgerReport.getRepaymentDetailList();
//                for (RepaymentDetail repaymentDetail : repaymentDetailList) {
//
//                    model.addRow(new Object[]{repaymentDetail.getDueDate() + "" == null ? "__" : repaymentDetail.getDueDate() + "",
//                            repaymentDetail.getPayDate() + "" == null ? "__" : repaymentDetail.getPayDate() + "",
//                            repaymentDetail.getDueAmount() + "" == null ? "__" : repaymentDetail.getDueAmount() + "",
//                            ledgerReport.getInterestRate() + "" == null ? "__" : ledgerReport.getInterestRate() + "",
//                            repaymentDetail.getRepaymentdetailid() + "" == null ? "__" : repaymentDetail.getRepaymentdetailid() + "",
//                            repaymentDetail.getPaymentAmount() + "" == null ? "__" : repaymentDetail.getPaymentAmount() + "",
//                            ledgerReport.getTotalPaid() + "" == null ? "__" : ledgerReport.getTotalPaid() + "",
//                            repaymentDetail.getBalanceAmount() + "" == null ? "__" : repaymentDetail.getBalanceAmount() + ""});
//                }
//                ds = new JRTableModelDataSource(model);
//
//            }
//        }
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LedgerReport.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename = LedgerReport.pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    /**
//     * getCollectionSummery
//     *
//     * @param branchId
//     * @param centerId
//     * @param firstDate
//     * @param secondDate
//     * @return
//     * @throws ParseException
//     */
//    @RequestMapping(value = "getCollectionSummery", method = RequestMethod.GET, headers = "Accept=application/json")
//    public void getCollectionSummery(HttpSession session,
//                                     HttpServletResponse response,
//                                     @RequestParam(value = "branchId") String branchId,
//                                     @RequestParam(value = "centerId") String centerId,
//                                     @RequestParam(value = "firstDate") String firstDate,
//                                     @RequestParam(value = "secondDate") String secondDate,
//                                     @RequestParam("img") String img) throws ParseException {
//
//        String param = centerId;
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("payDate");
//        model.addColumn("centermember");
//        model.addColumn("amount");
//        model.addColumn("due");
//        model.addColumn("balance");
//
//
//        List<RepaymentDetail> repaymentDetailList = repaymentDetailDAOService.getCollectionSummery(
//                branchId,
//                centerId,
//                firstDate,
//                secondDate, null, null);
//
//        Double amountTotal = 0.0;
//        Double dueTotal = 0.0;
//        Double balanceTotal = 0.0;
//
//        for (RepaymentDetail repaymentDetail : repaymentDetailList) {
//            if (branchId == null || branchId.isEmpty()) {
//                map.put("param1", "Customer");
//                map.put("status", "by Customer");
//                Loan loan = loanDAOService.getDetailsById(repaymentDetail.getLoanId());
//                if (loan != null) {
//                    Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//                    if (customer != null) {
//                        param = customer.getName();
//                    }
//                }
//            } else {
//                map.put("param1", "Center");
//                map.put("status", "by Branch");
//                Loan loan = loanDAOService.getDetailsById(repaymentDetail.getLoanId());
//                if (loan != null) {
//                    param = loan.getCenterid();
//                }
//            }
//
//            model.addRow(new Object[]{new SimpleDateFormat("yyyy-MM-dd").format(new Date(repaymentDetail.getPayDate().getTime())) + "", param,
//                    repaymentDetail.getPaymentAmount() == null ? 0.00 : repaymentDetail.getPaymentAmount().doubleValue(),
//                    repaymentDetail.getDueAmount() == null ? 0.00 : repaymentDetail.getDueAmount().doubleValue(),
//                    repaymentDetail.getBalanceAmount() == null ? 0.00 : repaymentDetail.getBalanceAmount().doubleValue()});
//
//            if (repaymentDetail.getPaymentAmount() != null) {
//                amountTotal += repaymentDetail.getPaymentAmount().doubleValue();
//            }
//            if (repaymentDetail.getDueAmount() != null) {
//                dueTotal += repaymentDetail.getDueAmount().doubleValue();
//            }
//            if (repaymentDetail.getBalanceAmount() != null) {
//                balanceTotal += repaymentDetail.getBalanceAmount().doubleValue();
//            }
//        }
//
//        map.put("amountTotal", amountTotal);
//        map.put("dueTotal", dueTotal);
//        map.put("balanceTotal", balanceTotal);
//        ds = new JRTableModelDataSource(model);
//
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("CollectionSummery.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=CollectionSummery.pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    /**
//     * getLoanSummery
//     *
//     * @param session
//     * @param response
//     * @param firstDate
//     * @param secondDate
//     * @param centerId
//     * @param branchId
//     */
//    @RequestMapping(value = "getLoanSummery", method = RequestMethod.GET, headers = "Accept=application/json")
//    public void getLoanSummeryReport(HttpSession session,
//                                     HttpServletResponse response, @RequestParam("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstDate,
//                                     @RequestParam("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date secondDate,
//                                     @RequestParam("centerId") String centerId,
//                                     @RequestParam("branchId") String branchId
//    ) {
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String first = simpleDateFormat.format(firstDate);
//        String second = simpleDateFormat.format(secondDate);
//
//        map.put("status", "Report by " + centerId + " " + first + " to " + second);
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("date");
//        model.addColumn("cusCode");
//        model.addColumn("cusName");
//        model.addColumn("loanAmount");
//        model.addColumn("rate");
//        model.addColumn("totalAmount");
//        model.addColumn("duration");
//
//        List<LoanSummeryModel> loanSummeryModelList = loanController.getLoanSummeryModel(firstDate,
//                secondDate,
//                centerId,
//                branchId);
//
//        Double totalAmount = 0.0;
//        Double ratedAmount = 0.0;
//
//
//        for (LoanSummeryModel loanSummeryModel : loanSummeryModelList) {
//
//
//            model.addRow(new Object[]{new SimpleDateFormat("yyyy-MM-dd").format(new Date(loanSummeryModel.getLoanDate().getTime())) + "", loanSummeryModel.getCustomerCode(),
//                    loanSummeryModel.getCustomerName(), loanSummeryModel.getLoanAmount() + "",
//                    loanSummeryModel.getInterestRate() + "", loanSummeryModel.getTotalAmount() + "",
//                    loanSummeryModel.getDurationByDays() + " " + loanSummeryModel.getDuration()});
//        }
//
//        double profit = ratedAmount - totalAmount;
//
//        map.put("totalAmount", totalAmount + "");
//        map.put("ratedAmount", ratedAmount + "");
//        map.put("totalProfit", profit + "");
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanSummery.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=LoanSummery.pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    /**
//     * getLoanSummeryModel
//     *
//     * @param firstDate
//     * @param secondDate
//     * @param centerId
//     * @param branchId
//     * @return
//     */
//    @RequestMapping(value = "getLoanSummeryModel", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public List<LoanSummery> getLoanSummeryModel(@RequestParam("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstDate,
//                                                 @RequestParam("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date secondDate,
//                                                 @RequestParam("centerId") String centerId,
//                                                 @RequestParam("branchId") String branchId
//    ) {
//
//        List<LoanSummery> summeryList = new ArrayList<LoanSummery>();
//
//        List<LoanSummeryModel> loanSummeryModelList = loanController.getLoanSummeryModel(firstDate,
//                secondDate,
//                centerId,
//                branchId);
//
//        for (LoanSummeryModel loanSummeryModel : loanSummeryModelList) {
//
//            LoanSummery loanSummery = new LoanSummery();
//
//            loanSummery.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(loanSummeryModel.getLoanDate().getTime())));
//            loanSummery.setCusCode(loanSummeryModel.getCustomerCode());
//            loanSummery.setCusName(loanSummeryModel.getCustomerName());
//            loanSummery.setLoanAmount(loanSummeryModel.getLoanAmount());
//            loanSummery.setTotalAmount(loanSummeryModel.getTotalAmount());
//            loanSummery.setDuration(loanSummeryModel.getDurationByDays() + " " + loanSummeryModel.getDuration());
//
//            summeryList.add(loanSummery);
//
//        }
//        return summeryList;
//
//    }
//
//
//    /**
//     * getUnderPaymentSummery
//     *
//     * @param session
//     * @param response
//     * @param branchId
//     * @param centerId
//     * @param firstDate
//     * @param secondDate
//     * @throws ParseException
//     */
//    @RequestMapping(value = "getUnderPaymentSummery", method = RequestMethod.GET, headers = "Accept=application/json")
//    public void getUnderPaymentSummery(HttpSession session,
//                                       HttpServletResponse response,
//                                       @RequestParam(value = "branchId") String branchId,
//                                       @RequestParam(value = "centerId") String centerId,
//                                       @RequestParam(value = "firstDate") String firstDate,
//                                       @RequestParam(value = "secondDate") String secondDate,
//                                       @RequestParam(value = "type") Integer type
//
//    ) throws ParseException {
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        map.put("center", centerId);
//        map.put("status", "");
//        map.put("fromDate", firstDate);
//        map.put("toDate", secondDate);
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("personName");
//        model.addColumn("contactNo");
//        model.addColumn("dueAmount");
//        model.addColumn("dueDate");
//
//        List<RepaymentDetail> repaymentDetailList = null;
//
//        if (type == 0) {
//            repaymentDetailList = repaymentDetailDAOService.getUnderPaymentSummery(
//                    branchId,
//                    centerId,
//                    firstDate,
//                    secondDate, null, null);
//            map.put("status", "NOT PAID");
//        } else if (type == 1) {
//            map.put("status", "PAID");
//            repaymentDetailList = repaymentDetailDAOService.getCollectionSummery(
//                    branchId,
//                    centerId,
//                    firstDate,
//                    secondDate, null, null);
//        }
//
//        Double amountTotal = 0.0;
//        Double dueTotal = 0.0;
//        Double balanceTotal = 0.0;
//
//        for (RepaymentDetail repaymentDetail : repaymentDetailList) {
//            String param = null;
//            String contactNum = null;
//
//            Loan loan = loanDAOService.getDetailsById(repaymentDetail.getLoanId());
//            if (loan != null) {
//                Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//                if (customer != null) {
//                    param = customer.getName();
//                    contactNum = customer.getTpNo();
//                }
//            }
//
//            model.addRow(new Object[]{param,
//                    contactNum,
//                    repaymentDetail.getDueAmount() == null ? "0.00" : repaymentDetail.getDueAmount().doubleValue() + "",
//                    new SimpleDateFormat("yyyy-MM-dd").format(new Date(repaymentDetail.getDueDate().getTime())) + ""});
//
//
//            if (repaymentDetail.getPaymentAmount() != null) {
//                amountTotal += repaymentDetail.getPaymentAmount().doubleValue();
//            }
//            if (repaymentDetail.getDueAmount() != null) {
//                dueTotal += repaymentDetail.getDueAmount().doubleValue();
//            }
//            if (repaymentDetail.getBalanceAmount() != null) {
//                balanceTotal += repaymentDetail.getBalanceAmount().doubleValue();
//            }
//        }
//
////        map.put("amountTotal", amountTotal);
////        map.put("dueTotal", dueTotal);
////        map.put("balanceTotal", balanceTotal);
//        ds = new JRTableModelDataSource(model);
//
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("UnderPaymentSummery.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=UnderPaymentSummery.pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    /**
//     * getUnderPaymentModel
//     *
//     * @param branchId
//     * @param centerId
//     * @param firstDate
//     * @param secondDate
//     * @param type
//     * @return
//     * @throws ParseException
//     */
//    @RequestMapping(value = "getUnderPaymentModel", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public List<UnderPaymentModel> getUnderPaymentModel(
//            @RequestParam(value = "branchId") String branchId,
//            @RequestParam(value = "centerId") String centerId,
//            @RequestParam(value = "firstDate") String firstDate,
//            @RequestParam(value = "secondDate") String secondDate,
//            @RequestParam(value = "type") Integer type,
//            @RequestParam(value = "limit") Integer limit,
//            @RequestParam(value = "offset") Integer offset
//    ) throws ParseException {
//
//        List<UnderPaymentModel> underPaymentModels = new ArrayList<UnderPaymentModel>();
//
//        List<RepaymentDetail> repaymentDetailList = null;
//
//        if (type == 0) {
//            repaymentDetailList = repaymentDetailDAOService.getUnderPaymentSummery(
//                    branchId,
//                    centerId,
//                    firstDate,
//                    secondDate, limit, offset);
//
//        } else if (type == 1) {
//
//            repaymentDetailList = repaymentDetailDAOService.getCollectionSummery(
//                    branchId,
//                    centerId,
//                    firstDate,
//                    secondDate, limit, offset);
//        }
//
//
//        for (RepaymentDetail repaymentDetail : repaymentDetailList) {
//            String param = null;
//            String contactNum = null;
//
//            Loan loan = loanDAOService.getDetailsById(repaymentDetail.getLoanId());
//            if (loan != null) {
//                Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//                if (customer != null) {
//                    param = customer.getName();
//                    contactNum = customer.getTpNo();
//                }
//            }
//
//            UnderPaymentModel underPaymentModel = new UnderPaymentModel();
//            underPaymentModel.setPersonName(param);
//            underPaymentModel.setDueAmount(repaymentDetail.getDueAmount() == null ? 0.00 : repaymentDetail.getDueAmount().doubleValue());
//            underPaymentModel.setDueDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(repaymentDetail.getDueDate().getTime())) + "");
//
//
//            underPaymentModels.add(underPaymentModel);
//
//
//        }
//        return underPaymentModels;
//
//
//    }
//
//
//    /**
//     * getUnderPaymentModelCount
//     *
//     * @param branchId
//     * @param centerId
//     * @param firstDate
//     * @param secondDate
//     * @param type
//     * @return
//     * @throws ParseException
//     */
//    @RequestMapping(value = "getUnderPaymentModelCount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Integer getUnderPaymentModelCount(
//            @RequestParam(value = "branchId") String branchId,
//            @RequestParam(value = "centerId") String centerId,
//            @RequestParam(value = "firstDate") String firstDate,
//            @RequestParam(value = "secondDate") String secondDate,
//            @RequestParam(value = "type") Integer type
//
//    ) throws ParseException {
//        Integer repaymentCount = null;
//
//        if (type == 0) {
//            repaymentCount = repaymentDetailDAOService.getUnderPaymentSummeryCount(
//                    branchId,
//                    centerId,
//                    firstDate,
//                    secondDate);
//
//        } else if (type == 1) {
//
//            repaymentCount = repaymentDetailDAOService.getCollectionSummeryCount(
//                    branchId,
//                    centerId,
//                    firstDate,
//                    secondDate);
//        }
//        return repaymentCount;
//
//    }
//
//    /**
//     * getLoanInvoice
//     *
//     * @param session
//     * @param response
//     * @param loanId
//     */
//    @RequestMapping(value = "getLoanInvoice", method = RequestMethod.GET)
//    public void loanInvoice(HttpSession session, HttpServletResponse response,
//                            @RequestParam("loanId") String loanId,
//                            @RequestParam("username") String username,
//                            @RequestParam("img") String img) {
//
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        Loan loan = loanDAOService.getDetailsById(loanId);
//        if (loan != null) {
//
//            Branch branch = branchDAOService.getDetailById(loan.getBranchid());
//            if (branch != null) {
//                map.put("address", branch.getAddress() == null ? "__" : branch.getTown() == null ? "__" : branch.getTown());
//                map.put("telNo", branch.getContactNum() == null ? "__" : branch.getContactNum());
//                map.put("fax", branch.getFax() == null ? "__" : branch.getFax());
//                map.put("email", branch.getEmail() == null ? "__" : branch.getEmail());
//            }
//        }
//
//        map.put("no", new Date().getTime() + "");
//        map.put("vatNo", "__");
//
//        map.put("receiptNo", loan.getCenterid() + "/");
//        map.put("contactNp", loan.getLoanCode());
//
//        Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//        if (customer != null) {
//            map.put("thanksName", customer.getInitials() == null ? "__" : customer.getInitials());
//            map.put("thanksAddress", customer.getAddress() == null ? "__" : customer.getAddress());
//        }
//
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("description");
//        model.addColumn("amount");
//        model.addColumn("vatNo");
//
//        model.addRow(new Object[]{"Loan Amount", loan.getAmount() + "", "0.0"});
//        model.addRow(new Object[]{"Rental Amount", loan.getDefaultRental() + "", "0.0"});
//
//        map.put("totalAmount", loan.getAmount() + "");
//        map.put("paymentMode", "CHQ");
//        //map.put("simpleAmount", "50.00");
//        map.put("cashier", username);
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanInvoice.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + loan.getCenterid() + "" + loan.getLoanid() + ".pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//
//    /**
//     * getDocumentInvoice
//     *
//     * @param session
//     * @param response
//     * @param username
//     * @param applicationId
//     * @param documentChargesId
//     */
//    @RequestMapping(value = "getDocumentInvoice", method = RequestMethod.GET)
//    public void getDocumentInvoice(HttpSession session, HttpServletResponse response,
//                                   @RequestParam("username") String username,
//                                   @RequestParam("applicationId") String applicationId,
//                                   @RequestParam("documentChargesId") String documentChargesId,
//                                   @RequestParam("img") String img) {
//
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        Application application = applicationDAOService.getApplicationDetailsById(applicationId);
//        DocumentCharges documentCharges = documentChargesDAOService.getDocumentDetailById(documentChargesId);
//
//        map.put("no", new Date().getTime() + "");
//        map.put("vatNo", "-");
//
//
//        if (application != null) {
//            map.put("receiptNo", username + "/" + application.getCustomerId());
//            map.put("contactNp", "DOC" + application.getApplicationId());
//            Table customer = customerDAOService.getDetailsById(application.getCustomerId());
//            if (customer != null) {
//                map.put("address", customer.getAddress() == null ? "__" : customer.getAddress());
//                map.put("telNo", customer.getTpNo() == null ? "__" : customer.getTpNo());
//                map.put("fax", "-");
//                map.put("email", "-");
//
//                map.put("thanksName", customer.getInitials() == null ? "__" : customer.getInitials());
//                map.put("thanksAddress", customer.getAddress() == null ? "__" : customer.getAddress());
//            }
//            map.put("contactNp", application.getApplicationId());
//        }
////        map.put("receiptNo", loan.getCenterid() + "/");
////
////
////        map.put("amountText", "NINE HUNDRED FIFTY FIVE THOUSAND SEVEN HUNDRED ONLY");
//
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("description");
//        model.addColumn("amount");
//        model.addColumn("vatNo");
//
//        if (documentCharges != null) {
//            model.addRow(new Object[]{"Document Charges", documentCharges.getAmount() + "", "0.0"});
//        }
//        map.put("totalAmount", documentCharges.getAmount() + "");
//        //  map.put("paymentMode", "CHQ 955,700.00 CHEQUE 171433 SAMPATH BANK Homagama");
//        //  map.put("simpleAmount", "50.00");
//        map.put("cashier", username);
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanInvoice.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + "DOC" + application.getApplicationId() + ".pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//
//    }
//
//    /**
//     * getChequeInvoice
//     *
//     * @param session
//     * @param response
//     * @param username
//     * @param applicationId
//     * @param documentChargesId
//     */
//    @RequestMapping(value = "getChequeInvoice", method = RequestMethod.GET)
//    public void getChequeInvoice(HttpSession session, HttpServletResponse response,
//                                 @RequestParam("username") String username,
//                                 @RequestParam("applicationId") String applicationId,
//                                 @RequestParam("documentChargesId") String documentChargesId,
//                                 @RequestParam("img") String img) {
//
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        Application application = applicationDAOService.getApplicationDetailsById(applicationId);
//        DocumentCharges documentCharges = documentChargesDAOService.getDocumentDetailById(documentChargesId);
//
//        map.put("no", new Date().getTime() + "");
//        map.put("vatNo", "-");
//
//        if (application != null) {
//            map.put("contactNp", "CHQ" + application.getApplicationId());
//            Table customer = customerDAOService.getDetailsById(application.getCustomerId());
//            if (customer != null) {
//                map.put("address", customer.getAddress() == null ? "__" : customer.getAddress());
//                map.put("telNo", customer.getTpNo() == null ? "__" : customer.getTpNo());
//                map.put("fax", "-");
//                map.put("email", "-");
//
//                map.put("thanksName", customer.getInitials());
//                map.put("thanksAddress", customer.getAddress());
//            }
//            map.put("contactNp", application.getApplicationId());
//        }
////        map.put("receiptNo", loan.getCenterid() + "/");
////
////
////        map.put("amountText", "NINE HUNDRED FIFTY FIVE THOUSAND SEVEN HUNDRED ONLY");
//
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("description");
//        model.addColumn("amount");
//        model.addColumn("vatNo");
//
//        if (documentCharges != null) {
//            model.addRow(new Object[]{"Document Charges", documentCharges.getAmount() + "", "0.0"});
//        }
//        map.put("totalAmount", documentCharges.getAmount() + "");
//        //  map.put("paymentMode", "CHQ 955,700.00 CHEQUE 171433 SAMPATH BANK Homagama");
//        //  map.put("simpleAmount", "50.00");
//        map.put("cashier", username);
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanInvoice.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + "CHQ" + application.getApplicationId() + ".pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//
//    }
//
//    /**
//     * getCustomerReport
//     *
//     * @param session
//     * @param response
//     * @param centerId
//     */
//    @RequestMapping(value = "getCustomerReport", method = RequestMethod.GET)
//    public void getCustomerReport(HttpSession session, HttpServletResponse response,
//                                  @RequestParam("username") String username,
//                                  @RequestParam("centerId") String centerId,
//                                  @RequestParam("img") String img
//    ) {
//
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        map.put("centerName", centerId);
//        map.put("user", username);
//
//        List<Table> customerList = customerDAOService.getAllCustomersByCenterId(centerId, null, null);
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("code");
//        model.addColumn("nic");
//        model.addColumn("initials");
//        model.addColumn("tpNo");
//        model.addColumn("address");
//        model.addColumn("dob");
//
//        for (Table customer : customerList) {
//            model.addRow(new Object[]{customer.getCode(), customer.getNic(), customer.getInitials(), customer.getTpNo(), customer.getAddress() + "," + customer.getCity(), customer.getDob()});
//        }
//
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("CustomerReport.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + centerId + ".pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//
//    }
//
//
//    /**
//     * getRepaymentInvoice
//     *
//     * @param session
//     * @param response
//     * @param username
//     * @param repaymentId
//     */
//    @RequestMapping(value = "getRepaymentInvoice", method = RequestMethod.GET)
//    public void getRepaymentInvoice(HttpSession session, HttpServletResponse response,
//                                    @RequestParam("username") String username,
//                                    @RequestParam("repaymentId") String repaymentId,
//                                    @RequestParam("img") String img
//
//    ) {
//
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        RepaymentDetail repaymentDetail = repaymentDetailDAOService.getDetailsById(repaymentId);
//
//        map.put("no", new Date().getTime() + "");
//
//        map.put("vatNo", "-");
//
//        if (repaymentDetail != null) {
//            map.put("contactNp", "REP" + repaymentDetail.getRepaymentdetailid());
//            Loan loan = loanDAOService.getDetailsById(repaymentDetail.getLoanId());
//            if (loan != null) {
//                Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//
//                if (customer != null) {
//                    map.put("address", customer.getAddress() == null ? "__" : customer.getAddress());
//                    map.put("telNo", customer.getTpNo() == null ? "__" : customer.getTpNo());
//                    map.put("fax", "-");
//                    map.put("email", "-");
//
//                    map.put("thanksName", customer.getInitials());
//                    map.put("thanksAddress", customer.getAddress());
//
//                    map.put("receiptNo", customer.getCity() + "/" + loan.getLoanCode());
//                }
//            }
//            // map.put("contactNp", application.getApplicationId());
//        }
////        map.put("receiptNo", loan.getCenterid() + "/");
////
////
////        map.put("amountText", "NINE HUNDRED FIFTY FIVE THOUSAND SEVEN HUNDRED ONLY");
//
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("description");
//        model.addColumn("amount");
//        model.addColumn("vatNo");
//
//        if (repaymentDetail != null) {
//            model.addRow(new Object[]{"Payment Amount", repaymentDetail.getPaymentAmount() == null ? "0.0" : repaymentDetail.getPaymentAmount() + "", "0.0"});
//            model.addRow(new Object[]{"Balance Amount", repaymentDetail.getBalanceAmount() == null ? "0.0" : repaymentDetail.getBalanceAmount() + "", "0.0"});
//            model.addRow(new Object[]{"Due Amount", repaymentDetail.getDueAmount() == null ? "0.0" : repaymentDetail.getDueAmount() + "", "0.0"});
//        }
//        double v = 0.0;
//        if (repaymentDetail.getPaymentAmount() != null && repaymentDetail.getBalanceAmount() != null && repaymentDetail.getDueAmount() != null) {
//            v = repaymentDetail.getPaymentAmount().doubleValue() + repaymentDetail.getBalanceAmount().doubleValue() + repaymentDetail.getDueAmount().doubleValue();
//        }
//        map.put("totalAmount", v + "");
//
//        //  map.put("paymentMode", "CHQ 955,700.00 CHEQUE 171433 SAMPATH BANK Homagama");
//        //  map.put("simpleAmount", "50.00");
//        map.put("cashier", username);
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanInvoice.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + "CHQ" + repaymentDetail.getRepaymentdetailid() + ".pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//
//    }
//
//
//    @RequestMapping(value = "getReport", method = RequestMethod.GET)
//    public void getReport(HttpSession session, HttpServletResponse response,
//                          @RequestParam("username") String username,
//                          @RequestParam("repaymentId") String repaymentId,
//                          @RequestParam("img") String img
//    ) {
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        RepaymentDetail repaymentDetail = repaymentDetailDAOService.getDetailsById(repaymentId);
//
//        map.put("no", new Date().getTime() + "");
//
//        map.put("vatNo", "-");
//
//        if (repaymentDetail != null) {
//            map.put("contactNp", "REP" + repaymentDetail.getRepaymentdetailid());
//            Loan loan = loanDAOService.getDetailsById(repaymentDetail.getLoanId());
//            if (loan != null) {
//                Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//
//                if (customer != null) {
//                    map.put("address", customer.getAddress() == null ? "__" : customer.getAddress());
//                    map.put("telNo", customer.getTpNo() == null ? "__" : customer.getTpNo());
//                    map.put("fax", "-");
//                    map.put("email", "-");
//
//                    map.put("thanksName", customer.getInitials());
//                    map.put("thanksAddress", customer.getAddress());
//
//                    map.put("receiptNo", customer.getCity() + "/" + loan.getLoanCode());
//                }
//            }
//            // map.put("contactNp", application.getApplicationId());
//        }
////        map.put("receiptNo", loan.getCenterid() + "/");
////
////
////        map.put("amountText", "NINE HUNDRED FIFTY FIVE THOUSAND SEVEN HUNDRED ONLY");
//
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        model.addColumn("description");
//        model.addColumn("amount");
//        model.addColumn("vatNo");
//
//        if (repaymentDetail != null) {
//            model.addRow(new Object[]{"Payment Amount", repaymentDetail.getPaymentAmount() == null ? "0.0" : repaymentDetail.getPaymentAmount() + "", "0.0"});
//            model.addRow(new Object[]{"Balance Amount", repaymentDetail.getBalanceAmount() == null ? "0.0" : repaymentDetail.getBalanceAmount() + "", "0.0"});
//            model.addRow(new Object[]{"Due Amount", repaymentDetail.getDueAmount() == null ? "0.0" : repaymentDetail.getDueAmount() + "", "0.0"});
//        }
//        double v = 0.0;
//        if (repaymentDetail.getPaymentAmount() != null && repaymentDetail.getBalanceAmount() != null && repaymentDetail.getDueAmount() != null) {
//            v = repaymentDetail.getPaymentAmount().doubleValue() + repaymentDetail.getBalanceAmount().doubleValue() + repaymentDetail.getDueAmount().doubleValue();
//        }
//        map.put("totalAmount", v + "");
//
//        //  map.put("paymentMode", "CHQ 955,700.00 CHEQUE 171433 SAMPATH BANK Homagama");
//        //  map.put("simpleAmount", "50.00");
//        map.put("cashier", username);
//
//        ds = new JRTableModelDataSource(model);
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("LoanInvoice.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".html");
//            JasperExportManager.exportReportToHtmlFile(jp, pdf.getName());
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/html");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + "CHQ" + repaymentDetail.getRepaymentdetailid() + ".html");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//
//    }
//
//    /**
//     * getPromissaryNote
//     *
//     * @param session
//     * @param response
//     * @param loanId
//     */
//    @RequestMapping(value = "getPromissaryNote", method = RequestMethod.GET)
//    public void getPromissaryNote(HttpSession session, HttpServletResponse response,
//                                  @RequestParam("loanId") String loanId
//    ) {
//
//        String customerCode = "";
//
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//        Loan loan = loanDAOService.getDetailsById(loanId);
//        if (loan != null) {
//            map.put("amount", loan.getAmount() + "");
//            Words w = Words.getInstance(loan.getAmount().longValue());
//            map.put("amountText", w.getNumberInWords() + " " + "Only");
//            Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//            if (customer != null) {
//                customerCode = customer.getCode();
//                map.put("address", customer.getAddress() + "," + customer.getCity());
//                map.put("name", customer.getName());
//                map.put("nic", customer.getNic());
//                map.put("memCode", customer.getCode());
//            }
//            Package aPackage = packageDAOService.getPackageById(loan.getPackageid());
//            if (aPackage != null) {
//                map.put("in", aPackage.getRate().longValue() + "");
////                double v = loan.getAmount().doubleValue() * aPackage.getRate().doubleValue()/100;
////                map.put("interestText",v+"");
//                Words w1 = Words.getInstance(aPackage.getRate().longValue());
//                map.put("interestText", w1.getNumberInWords() + "");
//            }
//
//            Map m1 = new HashMap();
//
//            Table customerOne = null;
//            Table customerTwo = null;
//            List<LoanHasGuarantee> loanHasGuarantees = loanHasGuaranteeDAOService.getByLoan(loanId);
//            for (LoanHasGuarantee loanHasGuarantee : loanHasGuarantees) {
//
//                LoanHasGuarantee hasGuarantee = loanHasGuaranteeDAOService.getDetailsById(loanHasGuarantee.getLoanHasGuaranteeId());
//                if (hasGuarantee != null) {
//                    customerOne = customerDAOService.getDetailsById(hasGuarantee.getGuaranteeId());
//                    m1.put(customerOne.getCode(), customerOne);
//                }
//            }
//
//
//            map.put("gName1", "--");
//            map.put("gNic1", "--");
//            map.put("gAddress1", "--");
//
//            for (Object key : m1.keySet()) {
//                Table customer1 = (Table) m1.get(key);
//
//                map.put("gName1", customer1.getInitials() == null ? "--" : customer1.getInitials());
//                map.put("gNic1", customer1.getNic() == null ? "--" : customer1.getNic());
//                map.put("gAddress1", customer1.getAddress() == null ? "--" : customer1.getAddress()+ "," + customer1.getCity());
//
//                Object remove = m1.remove(key);
//                break;
//
//            }
//
//
//            map.put("gName2", "--");
//            map.put("gNic2", "--");
//            map.put("gAddress2", "--");
//
//            for (Object key : m1.keySet()) {
//                Table customer2 = (Table) m1.get(key);
//                map.put("gName2", customer2.getInitials() == null ? "--" : customer2.getInitials());
//                map.put("gNic2", customer2.getNic() == null ? "--" : customer2.getNic());
//                map.put("gAddress2", customer2.getAddress() == null ? "--" : customer2.getAddress()+ "," + customer2.getCity());
//
//                Object remove = m1.remove(key);
//                break;
//
//            }
//
//            map.put("gName3", "--");
//            map.put("gNic3", "--");
//            map.put("gAddress3", "--");
//
//            for (Object key : m1.keySet()) {
//
//                Table customer3 = (Table) m1.get(key);
//
//                map.put("gName3", customer3.getInitials() == null ? "--" : customer3.getInitials());
//                map.put("gNic3", customer3.getNic() == null ? "--" : customer3.getNic());
//                map.put("gAddress3", customer3.getAddress() == null ? "--" : customer3.getAddress()+ "," + customer3.getCity());
//
//                Object remove = m1.remove(key);
//                break;
//
//            }
//
//            map.put("gName4", "--");
//            map.put("gNic4", "--");
//            map.put("gAddress4", "--");
//
//            for (Object key : m1.keySet()) {
//                Table customer4 = (Table) m1.get(key);
//                map.put("gName4", customer4.getInitials() == null ? "--" : customer4.getInitials());
//                map.put("gNic4", customer4.getNic() == null ? "--" : customer4.getNic());
//                map.put("gAddress4", customer4.getAddress() == null ? "--" : customer4.getAddress()+ "," + customer4.getCity());
//
//                Object remove = m1.remove(key);
//                break;
//
//            }
//
//
//        }
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("sinhalaReportFinal.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//            try {
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//                response.setHeader("Content-Disposition", "attachment; filename = PromissaryNote_" + customerCode + ".pdf");
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//
//    /**
//     * getPaymentVoucher
//     *
//     * @param session
//     * @param response
//     * @param loanId
//     */
//    @RequestMapping(value = "getPaymentVoucher", method = RequestMethod.GET)
//    public void getPaymentVoucher(HttpSession session, HttpServletResponse response,
//                                  @RequestParam("loanId") String loanId, @RequestParam("img") String img
//    ) {
//        JRTableModelDataSource ds = null;
//        Map<String, Object> map = null;
//        map = new HashMap<String, Object>();
//
//        URL imgurl = getClass().getClassLoader().getResource(img);
//        map.put("imagePath", imgurl + "");
//
//        Loan loan = loanDAOService.getDetailsById(loanId);
//        if (loan != null) {
//            map.put("amount", loan.getAmount() + "");
//            Words w = Words.getInstance(loan.getAmount().longValue());
//            map.put("sumRupees", w.getNumberInWords() + " " + "Only");
//            Branch branch = branchDAOService.getDetailById(loan.getBranchid());
//            if (branch != null) {
//                map.put("branch", branch.getTown());
//            }
//            Table customer = customerDAOService.getDetailsById(loan.getCustomerid());
//            if (customer != null) {
//                map.put("pvNo", customer.getCode());
//                map.put("payee", customer.getInitials());
//            }
//        }
//        try {
//            InputStream systemResourceAsStream = this.getClass().getClassLoader().getResourceAsStream("voucher.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(systemResourceAsStream);
//            JasperPrint jp = JasperFillManager.fillReport(jr, map, ds);
//            // JasperViewer.viewReport(jp, false);
//            File pdf = File.createTempFile("output.", ".pdf");
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
//
//            try {
//
//                InputStream inputStream = new FileInputStream(pdf);
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename = PaymentVoucher-" + loan.getLoanCode() + ".pdf");
//
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//                inputStream.close();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//    }
//
//
//}
