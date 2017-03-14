package org.egreen.opensms.server.controller;


import org.egreen.opensms.server.entity.*;

import org.egreen.opensms.server.models.BranchDetailModel;

import org.egreen.opensms.server.models.LoanDetailModel;
import org.egreen.opensms.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dewmal on 7/17/14.
 */
@Controller
@RequestMapping("openmicro/v1/dashboard")
public class DashBoardController {

//    @Autowired
//    private LoanDAOService loanDAOService;
//
//    @Autowired
//    private PackageDAOService packageDAOService;

    @Autowired
    private TableDAOService customerDAOService;

//    @Autowired
//    private BranchDAOService branchDAOService;
//
//    @Autowired
//    private CenterDAOService centerDAOService;
//
//    @Autowired
//    private RepaymentDetailDAOService repaymentDetailDAOService;
//
//    @Autowired
//    private BusinessTypeDAOService businessTypeDAOService;
//
//    @Autowired
//    private TypeDAOService typeDAOService;


    /**
     * getMemberCount
     * .
     *
//     * @return
//     */
//    @RequestMapping(value = "getMemberCount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Long getMemberCount() {
////        return customerDAOService.getCustomerCount();
////    }
////
////    /**
//     * getIssuedLoanAmount
//     *
//     * @return
//     */
//    @RequestMapping(value = "getIssuedLoanAmount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Double getIssuedLoanAmount() {
//        List<Loan> all = loanDAOService.getAll();
//
//        Double totalAmount = 0.0;
//        for (Loan loan : all) {
//            if (loan.getAmount() != null) {
//                totalAmount += loan.getAmount().doubleValue();
//            }
//        }
//
//        return totalAmount;
//    }


    /**
     * getAllPendingLoansCount
     *
     * @return
//     */
//    @RequestMapping(value = "getAllPendingLoansCount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Long getAllPendingLoansCount() {
//        return loanDAOService.getAllPendingLoansCount();
//    }
//

    /**
     * getAllNotPaidLoansCount
     *
     * @return
     */
//    @RequestMapping(value = "getAllNotPaidLoansCount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Long getAllNotPaidLoansCount() {
//
//        return loanDAOService.getAllNotPaidLoansCount();
//    }


    /**
     * getPaidLoansAmount
     * <p/>
     * 0 = Not Paid
     * 1 = Paid
     * 2 = Under Paid
     *
     * @param type
     * @return
     */
//    @RequestMapping(value = "getPaidLoansAmount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public BigDecimal getRepayments(@RequestParam("type") Integer type) {
//
//        BigDecimal amount = repaymentDetailDAOService.getRepayments(type);
////        Double amount = 0.0;
////        for (RepaymentDetail repaymentDetail : repaymentDetailList) {
////            if (repaymentDetail.getPaymentAmount()!=null){
////                amount +=  repaymentDetail.getPaymentAmount().doubleValue();
////            }
////        }
//        return amount;
//
//    }


    /**
     * getLoansCount
     * <p/>
     * 0 = Not Paid
     * 1 = Paid
     * 2 = Under Paid
     *
     * @param type
     * @return
     */
//    @RequestMapping(value = "getLoansCount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Long getLoansCount(@RequestParam("type") Integer type) {
//
//        Long amount = repaymentDetailDAOService.getLoansCount(type);
////        Double amount = 0.0;
////        for (RepaymentDetail repaymentDetail : repaymentDetailList) {
////            if (repaymentDetail.getPaymentAmount()!=null){
////                amount +=  repaymentDetail.getPaymentAmount().doubleValue();
////            }
////        }
//        return amount;
//
//    }


//    /**
//     * getCollectionTotalAmount
//     *
//     * @param branchId
//     * @return
//     */
//    @RequestMapping(value = "getCollectionTotalAmount", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public BigDecimal getCollectionTotalAmount(@RequestParam("branchId") String branchId) {
//        return repaymentDetailDAOService.getCollectionTotalAmount(branchId);
//    }

    /**
     * getLoanDetailModelByCenterId
     *
     * @param centerId
     * @param limit
     * @param offset
     * @return
     */
//
//
//    /**
//     * getLoanDetailCountByCenterId
//     *
//     * @return
//     */
//    @RequestMapping(value = "getBranchDetailsModel", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public List<BranchDetailModel> getCenterDetailsModel() {
//        List<BranchDetailModel> centerDetailModelList = new ArrayList<BranchDetailModel>();
//        List<Branch> branchList = branchDAOService.getAll();
//        for (Branch branch : branchList) {
//            BranchDetailModel branchDetailModel = new BranchDetailModel();
//            Long totalMember = customerDAOService.getAllCountByBranch(branch.getBranchid());
//            Long deactiveLoans = customerDAOService.getMemberCountByStatus(branch.getBranchid(), 0);
//            Long activeLoans = customerDAOService.getMemberCountByStatus(branch.getBranchid(), 1);
//            branchDetailModel.setBranchCode(branch.getBranchid());
//            branchDetailModel.setTotalMemCount(totalMember);
//            branchDetailModel.setDeActiveMemCount(deactiveLoans);
//            branchDetailModel.setActiveMemCount(activeLoans);
//            List<Loan> loanList =  loanDAOService.getAllOpenLoansByBranch(branch.getBranchid());
//            double totalDueAmount = 0;
//            for (Loan loan : loanList) {
//
//                if (loan!=null) {
//                    Package packageer = packageDAOService.getPackageById(loan.getPackageid());
//                    double v = 0;
//                    if (packageer != null) {
//                        v = loan.getAmount().doubleValue() * (100 + packageer.getRate().doubleValue()) / 100;
//                    }
//                    if (loan.getRecivedPayments() != null) {
//                        totalDueAmount += v - loan.getRecivedPayments().doubleValue();
//                    }
//                }
//            }
//            branchDetailModel.setPendingLoanAmount(BigDecimal.valueOf(totalDueAmount));
//            centerDetailModelList.add(branchDetailModel);
//        }
//        return centerDetailModelList;
//    }
//
//
//    @RequestMapping(value = "getLoanDetailCountByCenterId", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Long getLoanDetailCountByCenterId(@RequestParam("centerId") String centerId) {
//        Long aLong = loanDAOService.getOpenLoanCountByCenterID(centerId);
//        return aLong;
//    }


//    @RequestMapping(value = "ob", method = RequestMethod.GET, headers = "Accept=application/json")
//    @ResponseBody
//    public Loan getob() {
//        return new Loan();
//    }


}
