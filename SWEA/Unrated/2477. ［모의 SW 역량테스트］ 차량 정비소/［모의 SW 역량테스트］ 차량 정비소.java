import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int TestCaseNum, ReceptionNum, RepairNum, CustomerNum, TargetReceptionNo, TargetRepairNo;
    static int[] receptionTime, repairTime;

    static class Customer {
        int no, arriveTime, waitTime, receptionNo, repairNo;

        public Customer(int no, int arriveTime) {
            this.no = no;
            this.arriveTime = arriveTime;
            this.waitTime = 0;
            this.receptionNo = 0;
            this.repairNo = 0;
        }
    }

    static PriorityQueue<Customer> receptionQ, repairQ;
    static int sumCustomerNo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        TestCaseNum = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TestCaseNum; tc++) {
            // 기본 조건 입력 받기
            st = new StringTokenizer(br.readLine().trim());
            ReceptionNum = Integer.parseInt(st.nextToken());
            RepairNum = Integer.parseInt(st.nextToken());
            CustomerNum = Integer.parseInt(st.nextToken());
            TargetReceptionNo = Integer.parseInt(st.nextToken());
            TargetRepairNo = Integer.parseInt(st.nextToken());

            // 접수 시간 입력받기
            st = new StringTokenizer(br.readLine().trim());
            receptionTime = new int[ReceptionNum];
            for (int i = 0; i < ReceptionNum; i++) {
                receptionTime[i] = Integer.parseInt(st.nextToken());
            }

            // 정비 시간 입력받기
            st = new StringTokenizer(br.readLine().trim());
            repairTime = new int[RepairNum];
            for (int i = 0; i < RepairNum; i++) {
                repairTime[i] = Integer.parseInt(st.nextToken());
            }

            // 고객 도착 정보 입력 받기
            receptionQ = new PriorityQueue<>((o1, o2) -> {
                int diffTime = o1.arriveTime - o2.arriveTime;
                int diffNum = o1.no - o2.no;
                return diffTime == 0 ? diffNum : diffTime;
            });
            st = new StringTokenizer(br.readLine().trim());
            for (int cusNo = 1; cusNo <= CustomerNum; cusNo++) {
                int time = Integer.parseInt(st.nextToken());
                receptionQ.add(new Customer(cusNo, time));
            }

            repairQ = new PriorityQueue<>((o1, o2) -> {
                int diffTime = o1.arriveTime - o2.arriveTime;
                int diffReceptNo = o1.receptionNo - o2.receptionNo;
                return diffTime == 0 ? diffReceptNo : diffTime;
            });

            simulation();
            answer.append("#").append(tc).append(" ").append(sumCustomerNo==0?-1:sumCustomerNo).append("\n");
        }
        System.out.println(answer);
    }

    private static void simulation() {
        sumCustomerNo = 0;
        int repairArriveCnt = 0;
        Customer[] reception = new Customer[ReceptionNum];
        Customer[] repair = new Customer[ReceptionNum];

        int time = 0;
        while (true) {
            // 1. 접수하기
            for (int i = 0; i < ReceptionNum; i++) {
                if (reception[i]!=null) {
                    Customer curCustomer = reception[i];
                    if (time >= curCustomer.arriveTime + curCustomer.waitTime) {
                        reception[i] = null;
                        curCustomer.arriveTime = time;
                        repairQ.add(curCustomer);
                    }
                }

                if (reception[i] == null) {
                    // 해당 접수 창구가 비어있으면 접수 큐를 확인해 접수한다.
                    if (!receptionQ.isEmpty() && time >= receptionQ.peek().arriveTime) {
                        reception[i] = receptionQ.poll();
                        reception[i].arriveTime = time;
                        reception[i].waitTime = receptionTime[i];
                        reception[i].receptionNo = i+1;
                    }
                }
            }

            // 2. 정비하기
            for (int i = 0; i < RepairNum; i++) {
                if (repair[i] != null) {
                    // 정비 창구가 비어있지 않으면 정비가 끝난 고객이 있는지 확인한다.
                    Customer curCustomer = repair[i];
                    if (time >= curCustomer.arriveTime + curCustomer.waitTime) {
                        repair[i] = null;
                    }
                }

                if (repair[i] == null) {
                    // 해당 정비 창구가 비어있으면 정비 큐를 확인해 접수한다.
                    if (!repairQ.isEmpty() && time >= repairQ.peek().arriveTime) {
                        repair[i] = repairQ.poll();
                        repair[i].arriveTime = time;
                        repair[i].waitTime = repairTime[i];
                        repair[i].repairNo = i+1;

                        // 정비 창구 번호를 배정받으면 타겟 일치 여부를 체크한다.
                        repairArriveCnt++;
                        if (repair[i].receptionNo == TargetReceptionNo && repair[i].repairNo == TargetRepairNo) {
                            sumCustomerNo += repair[i].no;
                        }
                        if (repairArriveCnt == CustomerNum) {   // 모든 고객이 정비 창구까지 배정받으면 더 볼 필요 없음.
                            return;
                        }
                    }
                }
            }

            time++;
        }
    }
}
