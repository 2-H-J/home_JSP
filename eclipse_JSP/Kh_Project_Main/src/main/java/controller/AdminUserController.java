package controller;

import java.io.IOException;
import java.util.List;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

public class AdminUserController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // action 파라미터로 동작 분기
        String action = request.getParameter("action");
        String memberIdParam = request.getParameter("memberId");

        System.out.println("[AdminUserController] 요청 시작, action: " + action + ", memberId: " + memberIdParam);

        try {
            // memberId가 존재하면 검색 처리
            if (memberIdParam != null && !memberIdParam.trim().isEmpty()) {
                System.out.println("[AdminUserController] 검색 조건 감지 - memberId: " + memberIdParam);
                return handleSearch(request);
            }

            // action에 따라 동작 분기
            if ("delete".equals(action)) {
                return handleDelete(request, response);
            } else if ("detail".equals(action)) {
                return handleDetail(request, response);
            } else if ("view".equals(action) || action == null) {
                return handleView();
            }
        } catch (Exception e) {
            System.out.println("[AdminUserController] 처리 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }

        // 기본 동작: 사용자 목록 조회
        return handleView();
    }

    /**
     * 사용자 삭제 처리
     */
    private ModelAndView handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int userNumber = Integer.parseInt(request.getParameter("userNumber"));
            System.out.println("[AdminUserController] 삭제 요청 userNumber: " + userNumber);

            // 사용자 삭제 시 게시물 등 연관 데이터도 삭제
            boolean result = UsersService.getInstance().deleteUserAndRelatedData(userNumber);
            if (result) {
                System.out.println("[AdminUserController] 사용자 및 관련 데이터 삭제 성공");
            } else {
                System.out.println("[AdminUserController] 사용자 삭제 실패: 연관 데이터 삭제 오류");
            }
        } catch (NumberFormatException e) {
            System.out.println("[AdminUserController] 잘못된 사용자 번호입니다: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[AdminUserController] 예외 발생: " + e.getMessage());
        }

        // 삭제 후 사용자 목록으로 리다이렉트
        response.sendRedirect("adminUser.do?action=view");
        return null;
    }

    /**
     * 사용자 상세보기 처리
     */
    private ModelAndView handleDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            System.out.println("[AdminUserController] 상세보기 처리 시작");
            int userNumber = Integer.parseInt(request.getParameter("userNumber"));
            System.out.println("[AdminUserController] 상세보기 요청 userNumber: " + userNumber);

            UsersDTO user = UsersService.getInstance().selectUserByNumber(userNumber);
            System.out.println("[AdminUserController] 조회된 사용자: " + user);

            if (user != null) {
                request.setAttribute("userDetail", user);
                ModelAndView view = new ModelAndView();
                view.setPath("user_detail.jsp");
                view.setRedirect(false);
                return view;
            } else {
                System.out.println("[AdminUserController] 사용자 정보를 찾을 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[AdminUserController] 잘못된 사용자 번호입니다: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[AdminUserController] 예외 발생: " + e.getMessage());
        }

        // 사용자 정보가 없거나 예외 발생 시 목록으로 리다이렉트
        response.sendRedirect("adminUser.do?action=view");
        return null;
    }

    /**
     * 사용자 목록 조회 처리
     */
    private ModelAndView handleView() {
        System.out.println("[AdminUserController] 사용자 목록 조회 시작");
        try {
            List<UsersDTO> list = UsersService.getInstance().selectAllUsers();
            System.out.println("[AdminUserController] 조회된 사용자 수: " + list.size());

            ModelAndView view = new ModelAndView();
            view.addObject("list", list);
            view.setPath("users_list.jsp");
            view.setRedirect(false);
            return view;
        } catch (Exception e) {
            System.out.println("[AdminUserController] 사용자 목록 조회 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 사용자 검색 처리
     */
    private ModelAndView handleSearch(HttpServletRequest request) {
        try {
            String memberIdParam = request.getParameter("memberId");
            System.out.println("[AdminUserController] 요청된 memberId: " + memberIdParam);

            if (memberIdParam != null && !memberIdParam.trim().isEmpty()) {
                int memberId = Integer.parseInt(memberIdParam);
                List<UsersDTO> list = UsersService.getInstance().selectUserAdminById(memberId);
                
                System.out.println("[AdminUserController] 검색된 사용자 수: " + list.size());

                ModelAndView view = new ModelAndView();
                view.addObject("list", list);
                view.setPath("users_list.jsp");
                view.setRedirect(false);
                return view;
            }
        } catch (NumberFormatException e) {
            System.out.println("[AdminUserController] 잘못된 memberId 형식: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[AdminUserController] 검색 처리 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }

        // 검색 실패 시 기본 사용자 목록 반환
        return handleView();
    }
}
